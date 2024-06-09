package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearned.databinding.ActivityAsynchronousPracticeBinding
import com.hjq.toast.Toaster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class AsynchronousPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAsynchronousPracticeBinding
    private val fixedThreadExecutor = Executors.newFixedThreadPool(3) // 适合少量任务，控制并发数量
    private val scheduledThreadExecutor = Executors.newScheduledThreadPool(3) // 适合定时、周期性的任务
    private val cachedThreadExecutor = Executors.newCachedThreadPool() // 适合执行数量多、耗时较少的任务
    private val singleThreadExecutor = Executors.newSingleThreadExecutor() // 适合执行单一任务

    private inner class MyHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Toaster.show("收到主线程发送过来的消息(${msg.what}):${msg.data.getString("msg")}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsynchronousPracticeBinding.inflate(layoutInflater)
        initThreadActions()
        initCoroutineActions()
        setContentView(binding.root)
    }

    private fun initThreadActions() {
        binding.thread1.setOnClickListener {
            val t = object : Thread() {
                override fun run() {
                    var account = 5
                    while (account > 0) {
                        sleep(1000)
                        Toaster.show("在单个子线程执行(${id}):$account")
                        account--

                    }
                }
            }
            t.start()
        }

        binding.thread2.setOnClickListener {
            val r = Runnable {
                var account = 10
                while (account > 0) {
                    Thread.sleep(1000)
                    Toaster.show("在两个子线程执行(${Thread.currentThread().id}):$account")
                    account--
                }
            }
            val t1 = Thread(r)
            val t2 = Thread(r)
            t1.start()
            t2.start()
        }

        binding.handle1.setOnClickListener {
            HandlerThread("child1", HandlerThread.MAX_PRIORITY).apply {
                start()
                val h = MyHandler(looper)
                h.sendMessage(Message.obtain().apply {
                    what = this@AsynchronousPracticeActivity.hashCode()
                    data = Bundle().apply {
                        putString("msg", "你好呀😄")
                    }
                })
            }
        }

        binding.handle2.setOnClickListener {
            val h = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Toaster.show("收到子线程发送过来的消息(${msg.what}):${msg.data.getString("msg")}")
                }
            }
            Thread {
                h.sendMessage(Message.obtain().apply {
                    what = Thread.currentThread().id.toInt()
                    data = Bundle().apply {
                        putString("msg", "哈喽哈喽")
                    }
                })
            }.start()

        }
        binding.threadPool1.setOnClickListener {
            fixedThreadExecutor.execute {
                var account = 5
                while (account > 0) {
                    Toaster.show("在线程池中执行(${Thread.currentThread().id}):$account")
                    Thread.sleep(1000)
                    account--
                }
            }
        }

        binding.threadPool2.setOnClickListener {
            scheduledThreadExecutor.schedule({
                Toaster.show("延迟一秒后的执行,")
            }, 1000, TimeUnit.MILLISECONDS) // 一次性任务
            var account = 1
            val future = scheduledThreadExecutor.scheduleWithFixedDelay({
                Toaster.show("每隔一秒执行一次${account}")
                account++
            }, 5000, 1000, TimeUnit.MILLISECONDS) // 周期性任务
            // 10秒后取消定时任务
            Handler(Looper.getMainLooper()).postDelayed({
                future.cancel(true)
            }, 10000)
        }

        binding.threadPool3.setOnClickListener {
            cachedThreadExecutor.execute {
                Toaster.show("执行任务啦(cachedThreadExecutor)")
            } // 一次性任务
        }

        binding.threadPool4.setOnClickListener {
            singleThreadExecutor.execute {
                Toaster.show("执行任务啦(singleThreadExecutor)")
            } // 一次性任务
        }


    }

    @OptIn(FlowPreview::class)
    private fun initCoroutineActions() {
        // 协程文档
        // 1.https://book.kotlincn.net/text/composing-suspending-functions.html
        // 2.https://developer.android.com/kotlin/coroutines?hl=zh-cn
        binding.coroutine1.setOnClickListener {
            // 运行新的协程并可中断地阻止当前(主)线程，直到其完成,只应该在测试中使用，不要在生产环境中使用
            Log.i("coroutine1", "执行协程1")
            runBlocking {
                Toaster.show("执行协程,这个协程会堵塞主线程")
            }
            Log.i("coroutine1", "执行协程2")
            // 这里加 handler 是因为Toaster方法执行太快
            Handler(Looper.getMainLooper()).postDelayed({
                Toaster.show("执行完毕")
            }, 1000)
        }

        binding.coroutine2.setOnClickListener {
            // 在子线程中执行协程，不堵塞主线程
            Log.i("coroutine2", "执行协程1")
            val job = CoroutineScope(Dispatchers.IO).launch {
                // 还能再协程中再起一个子协程
                launch(Dispatchers.Main) {
                    Toaster.show("（二）在协程中再起一个协程${Thread.currentThread().id}")
                    delay(1000)
                }
                repeat(10) {
                    Toaster.show("（一）执行协程${Thread.currentThread().id}->,${it}")
                    delay(1000)
                }
            }
            Log.i("coroutine2", "执行协程2")
            Handler(Looper.getMainLooper()).postDelayed({
                job.cancel()
                Toaster.show("取消协程")
            }, 6000)
        }

        binding.coroutine3.setOnClickListener {
            // 在主线程中执行协程
            Log.i("coroutine3", "执行协程1")
            val job = CoroutineScope(Dispatchers.Main).launch {
                withTimeout(3000) {
                    repeat(10) {
                        Toaster.show("执行协程->,${it}")
                        delay(1000)
                    }
                }
            }
            Log.i("coroutine3", "执行协程2")
        }

        // 之前协程里的代码都是同步执行的，也就是一行一行往下执行的，这里可以使用 async 支持并发执行
        binding.coroutine4.setOnClickListener {
            suspend fun doSomethingUsefulOne(): Int {
                delay(1000L) // 假设我们在这里做了些有用的事
                return 13
            }

            suspend fun doSomethingUsefulTwo(): Int {
                delay(1000L) // 假设我们在这里也做了些有用的事
                return 29
            }
            CoroutineScope(Dispatchers.IO).launch {
                val time = measureTimeMillis {
                    // 这里使用 async 并发执行
                    Log.i("coroutine4", "执行协程1")
                    val one = async { doSomethingUsefulOne() }
                    val two = async { doSomethingUsefulTwo() }
                    Log.i("coroutine4", "执行协程2")
                    println("The answer is ${one.await() + two.await()}")
                    Toaster.show("结果是 ${one.await() + two.await()}")
                }
                println("Completed in $time ms") // 不使用 async 会需要 2000ms左右，而使用 async 只会需要 1000ms左右
            }
        }

        // 已同步的方式执行异步代码, 一行一行往下执行
        binding.coroutine5.setOnClickListener {
            suspend fun doSomethingUsefulOne(): Int {
                return withContext(Dispatchers.IO) {
                    delay(1000L) // 假设我们在这里做了网络请求
                    13
                }
            }

            suspend fun doSomethingUsefulTwo(): Int {
                return withContext(Dispatchers.IO) {
                    delay(1000L) // 假设我们在这里做了网络请求
                    22
                }
            }
            CoroutineScope(Dispatchers.Main).launch { // 主线程
                Log.i("coroutine5", "执行协程1")
                val time = measureTimeMillis {
                    val result1 = doSomethingUsefulOne() // io线程
                    val result2 = doSomethingUsefulOne() // io线程
                    Toaster.show("值是->,${result1}--${result2}") // 主线程
                }
                Log.i("coroutine5", "执行协程2")
                println("Completed in $time ms") // 不使用 async 需要 2000ms左右
            }
        }

        // 已同步的方式执行异步代码，并发执行
        binding.coroutine6.setOnClickListener {
            suspend fun doSomethingUsefulOne(): Int {
                return withContext(Dispatchers.IO) {
                    delay(1000L) // 假设我们在这里做了网络请求
                    13
                }
            }

            suspend fun doSomethingUsefulTwo(): Int {
                return withContext(Dispatchers.IO) {
                    delay(1000L) // 假设我们在这里做了网络请求
                    22
                }
            }
            CoroutineScope(Dispatchers.Main).launch { // 主线程
                Log.i("coroutine6", "执行协程1")
                val time = measureTimeMillis {
                    val result1 = async { doSomethingUsefulOne() } // io线程
                    val result2 = async { doSomethingUsefulTwo() } // io线程
                    Toaster.show("值是->,${result1.await()}--${result2.await()}") // 主线程
                }
                Log.i("coroutine6", "执行协程2")
                println("Completed in $time ms") // 使用 async 需要 1000ms左右

            }
        }

        binding.coroutine7.setOnClickListener {
            // 创建一个流
            val flow = flow<Int> {
                var initValue = 10
                while (initValue > 0) {
                    emit(initValue)
                    initValue--
                    delay(1000)
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                // flot ->
                // 如果是一个空流，那么会触发onEmpty ->
                // 用给定条件，进行过滤 ->
                // 用给定函数，返回一个新流 ->
                // 遍历每一个数据，可以根据数据去进行一些操作，但不改变数据本身 ->
                flow
                    .onEmpty {
                        // 这里不是空流，所以永远不会触发
                        Log.i("coroutine7", "是一个空流")
                        emit(99)
                    }
                    .filter {
                        it % 2 == 0
                    }
                    .map {
                        it * it
                    }
                    .onEach {
                        Log.i("coroutine7", "每一个值->,${it}")
                    }
                    .collect {
                        Toaster.show("值是->,${it}")
                    }
            }


        }

        binding.coroutine8.setOnClickListener {
            val flow = flow<Int> {
                var initValue = 10
                while (initValue > 0) {
                    emit(initValue)
                    initValue--
                    delay(500)
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                // count，和collect一样，属于终端操作符
                val count = flow.count {
                    it % 2 == 0
                }
                Toaster.show("符合条件的数量是->,${count}")
            }
        }

        binding.coroutine9.setOnClickListener {
            val flow = flow<Int> {
                var initValue = 10
                while (initValue > 0) {
                    emit(initValue)
                    initValue--
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                // reduce，和collect一样，属于终端操作符
                val reduce = flow.reduce { accumulator, value ->
                    accumulator + value
                }
                Toaster.show("最终结果是->,${reduce}")
            }
        }

        binding.coroutine10.setOnClickListener {
            val flow = flow<Int> {
                var initValue = 10
                while (initValue > 0) {
                    emit(initValue)
                    initValue--
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                // fold，和collect一样，属于终端操作符
                // fold的和 reduce差不多，就是多了一个初始值
                val fold = flow.fold(100) { accumulator, value ->
                    accumulator + value
                }
                Toaster.show("最终结果是->,${fold}")
            }
        }

        binding.coroutine11.setOnClickListener {
            // 内部流
            fun requestFlow(i: Int) = flow {
                emit("$i: First")
                delay(500) // 等待 500 毫秒
                emit("$i: Second")
            }
            CoroutineScope(Dispatchers.Main).launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                // 展平流,它们在等待内部流完成之后开始收集下一个值，也就是得等到当前内部流完成才能继续执行下一个内部流，如下面的示例所示：
                // 结果：
//                1: First at 121 ms from start
//                1: Second at 622 ms from start
//                2: First at 727 ms from start
//                2: Second at 1227 ms from start
//                3: First at 1328 ms from start
//                3: Second at 1829 ms from start
                (1..3).asFlow().onEach { delay(100) } // 每 100 毫秒发射一个数字
                    .flatMapConcat { requestFlow(it) }
                    .collect { value -> // 收集并打印， 拉平了，所以一共有能搜集到 6个值
                        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
                        Toaster.show("$value at ${System.currentTimeMillis() - startTime} ms from start")
                    }
            }

        }

        binding.coroutine12.setOnClickListener {
            fun requestFlow(i: Int) = flow {
                emit("$i: First")
                delay(500) // 等待 500 毫秒
                emit("$i: Second")
            }
            CoroutineScope(Dispatchers.Main).launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                // 展平流,它们不会等待内部流完成之后开始收集下一个值，而是并发执行，也就是会同时执行所有内部流，如下面的示例所示：
                // 结果：
//                1: First at 136 ms from start
//                2: First at 231 ms from start
//                3: First at 333 ms from start
//                1: Second at 639 ms from start
//                2: Second at 732 ms from start
//                3: Second at 833 ms from start
                (1..3).asFlow().onEach { delay(100) } // 每 100 毫秒发射一个数字
                    .flatMapMerge { requestFlow(it) }
                    .collect { value -> // 收集并打印
                        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
                        Toaster.show("$value at ${System.currentTimeMillis() - startTime} ms from start")
                    }
            }

        }

        binding.coroutine13.setOnClickListener {
            // flowOn函数用于更改流发射的线程上下文。 以下示例展示了更改流上下文的正确方法，该示例还通过打印相应线程的名字以展示它们的工作方式：
            val flow = flow<Int> {
                var initValue = 10
                while (initValue > 0) {
                    emit(initValue)
                    delay(1000)
                    initValue--
                }
            } // flow block Will be executed in IO
            CoroutineScope(Dispatchers.Main).launch {
                flow
                    .filter {
                        it % 2 == 0
                    } // filter Will be executed in IO
                    .flowOn(Dispatchers.IO)
                    .map {
                        it * it
                    } // map Will be executed in Default
                    .flowOn(Dispatchers.Default)
                    .collect {
                        Toaster.show("值是->,${it}")
                    }  // Will be executed in Main

            }

        }

        binding.coroutine14.setOnClickListener {
            // 缓冲
            fun simple(): Flow<Int> = flow {
                for (i in 1..3) {
                    delay(1000) // 假装我们异步等待了 100 毫秒
                    emit(i) // 发射下一个值
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val time = measureTimeMillis {
                    simple().buffer().collect { value ->
                        delay(3000) // 假装我们花费 300 毫秒来处理它
                        Toaster.show(value)
                    }
                }
                println("Collected in $time ms")
                Toaster.show("Collected in $time ms")
            }
        }

        binding.coroutine15.setOnClickListener {
            //  合并,当流代表部分操作结果或操作状态更新时，可能没有必要处理每个值，而是只处理最新的那个。在本示例中，当收集器处理它们太慢的时候， conflate 操作符可以用于跳过中间值。构建前面的示例：
//            我们看到，虽然第一个数字仍在处理中，但第二个和第三个数字已经产生，因此第二个是 conflated ，只有最新的（第三个）被交付给收集器：
//            1
//            3
//            Collected in 758 ms
            fun simple(): Flow<Int> = flow {
                for (i in 1..3) {
                    delay(100) // 假装我们异步等待了 100 毫秒
                    emit(i) // 发射下一个值
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                val time = measureTimeMillis {
                    simple()
                        .conflate() // 合并发射项，不对每个值进行处理
                        .collect { value ->
                            delay(300) // 假装我们花费 300 毫秒来处理它
                            Toaster.show(value)
                        }
                }
                println("Collected in $time ms")
                Toaster.show("Collected in $time ms")
            }
        }

        binding.coroutine16.setOnClickListener {
            // 处理最新值,   当发射器和收集器都很慢的时候，合并是加快处理速度的一种方式。它通过删除发射值来实现。 另一种方式是取消缓慢的收集器，并在每次发射新值的时候重新启动它。有
//            由于 collectLatest 的函数体需要花费 300 毫秒，但是新值每 100 秒发射一次，我们看到该代码块对每个值运行，但是只收集最后一个值：
//            Collecting 1
//            Collecting 2
//            Collecting 3
//            Done 3
//            Collected in 741 ms
            fun simple(): Flow<Int> = flow {
                for (i in 1..3) {
                    delay(100) // 假装我们异步等待了 100 毫秒
                    emit(i) // 发射下一个值
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                val time = measureTimeMillis {
                    simple()
                        .collectLatest { value ->  // 取消并重新发射最后一个值
                            delay(300) // 假装我们花费 300 毫秒来处理它
                            Toaster.show(value)
                            delay(1000)

                        }
                }
                println("Collected in $time ms")
                Toaster.show("Collected in ${time - 1000} ms")
            }
        }

        binding.coroutine17.setOnClickListener {
            val nums = (1..3).asFlow() // 数字 1..3
            val strs = flowOf("one", "two", "three") // 字符串
            CoroutineScope(Dispatchers.Main).launch {
                nums.zip(strs) { a, b -> "$a -> $b" } // 组合单个字符串
                    .collect { Toaster.show(it) } // 收集并打印
            }
        }

        binding.coroutine18.setOnClickListener {
             // 当流表示一个变量或操作的最新值时（请参阅相关小节 conflation），可能需要执行计算，
            // 这依赖于相应流的最新值，并且每当上游流产生值的时候都需要重新计算。这种相应的操作符家族称为 combine。
            val nums = (1..3).asFlow().onEach { delay(300) } // 发射数字 1..3，间隔 300 毫秒
            val strs = flowOf("one", "two", "three").onEach { delay(400) } // 每 400 毫秒发射一次字符串
            val startTime = System.currentTimeMillis() // 记录开始的时间
            CoroutineScope(Dispatchers.Main).launch {
                nums.combine(strs) { a, b -> "$a -> $b" } // 使用“combine”组合单个字符串
                    .collect { value -> // 收集并打印
                        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
                        Toaster.show("$value at ${System.currentTimeMillis() - startTime} ms from start")
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fixedThreadExecutor.shutdown()
        scheduledThreadExecutor.shutdown()
        cachedThreadExecutor.shutdown()
        singleThreadExecutor.shutdown()
    }
}