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
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class AsynchronousPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAsynchronousPracticeBinding
    private val fixedThreadExecutor =  Executors.newFixedThreadPool(3) // 适合少量任务，控制并发数量
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
            val h = object: Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Toaster.show("收到子线程发送过来的消息(${msg.what}):${msg.data.getString("msg")}")
                }
            }
            Thread{
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
            },1000, TimeUnit.MILLISECONDS) // 一次性任务
            var account = 1
            val future = scheduledThreadExecutor.scheduleWithFixedDelay({
                Toaster.show("每隔一秒执行一次${account}")
                account++
            },5000,1000,TimeUnit.MILLISECONDS) // 周期性任务
            // 10秒后取消定时任务
            Handler(Looper.getMainLooper()).postDelayed({
                future.cancel(true)
            },10000)
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
    private fun initCoroutineActions() {
        // 协程文档
        // 1.https://book.kotlincn.net/text/composing-suspending-functions.html
        // 2.https://developer.android.com/kotlin/coroutines?hl=zh-cn
        binding.coroutine1.setOnClickListener {
            // 运行新的协程并可中断地阻止当前(主)线程，直到其完成,只应该在测试中使用，不要在生产环境中使用
            Log.i("coroutine1","执行协程1")
            runBlocking {
                Toaster.show("执行协程,这个协程会堵塞主线程")
            }
            Log.i("coroutine1","执行协程2")
            // 这里加 handler 是因为Toaster方法执行太快
            Handler(Looper.getMainLooper()).postDelayed({
                Toaster.show("执行完毕")
            },1000)
        }

        binding.coroutine2.setOnClickListener {
            // 在子线程中执行协程，不堵塞主线程
            Log.i("coroutine2","执行协程1")
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
            Log.i("coroutine2","执行协程2")
            Handler(Looper.getMainLooper()).postDelayed({
                job.cancel()
                Toaster.show("取消协程")
            },6000)
        }

        binding.coroutine3.setOnClickListener {
            // 在主线程中执行协程
            Log.i("coroutine3","执行协程1")
            val job = CoroutineScope(Dispatchers.Main).launch {
                withTimeout(3000) {
                    repeat(10) {
                        Toaster.show("执行协程->,${it}")
                        delay(1000)
                    }
                }
            }
            Log.i("coroutine3","执行协程2")
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
                    Log.i("coroutine4","执行协程1")
                    val one = async { doSomethingUsefulOne() }
                    val two = async { doSomethingUsefulTwo() }
                    Log.i("coroutine4","执行协程2")
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
                Log.i("coroutine5","执行协程1")
                val time = measureTimeMillis {
                    val result1 = doSomethingUsefulOne() // io线程
                    val result2 = doSomethingUsefulOne() // io线程
                    Toaster.show("值是->,${result1}--${result2}") // 主线程
                }
                Log.i("coroutine5","执行协程2")
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
                Log.i("coroutine6","执行协程1")
                val time = measureTimeMillis {
                    val result1 = async { doSomethingUsefulOne() } // io线程
                    val result2 = async { doSomethingUsefulTwo() } // io线程
                    Toaster.show("值是->,${result1.await()}--${result2.await()}") // 主线程
                }
                Log.i("coroutine6", "执行协程2")
                println("Completed in $time ms") // 使用 async 需要 1000ms左右

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