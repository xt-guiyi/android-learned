package com.example.androidlearned.ui.center.activitys

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest.Companion.MIN_BACKOFF_MILLIS
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.androidlearned.R
import com.example.androidlearned.databinding.ActivityWorkManagerPracticeBinding
import com.google.common.util.concurrent.ListenableFuture
import com.hjq.toast.Toaster
import java.util.concurrent.TimeUnit

class WorkManagerPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkManagerPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerPracticeBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    fun init() {
        binding.wm1.setOnClickListener {
            val myWorkRequest = OneTimeWorkRequestBuilder<MyWorker1>()
//                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) // 设置WorkRequest的优先级为加急, 在 Android 12 之前版本,Worker必须实现 getForegroundInfo 方法。
                .build() // 定义WorkRequest
            WorkManager.getInstance(this).enqueue(myWorkRequest) // 把WorkRequest放入队列，提交到 WorkManager。
        }


        binding.wm2.setOnClickListener {
//            val saveRequest = PeriodicWorkRequestBuilder<MyWorker2>(1, TimeUnit.HOURS) // 一个小时执行一次
//            val saveRequest = PeriodicWorkRequestBuilder<MyWorker2>(1, TimeUnit.HOURS,15, TimeUnit.MINUTES) // 灵活时间段的定期工作,可在每小时的最后 15 分钟内运行的定期工作。
//            设置约束
//            val constraints = Constraints.Builder()
//                .setRequiresCharging(true) // 设置设备是否应充电才能 WorkRequest 运行。默认值为 false。
//                .setRequiredNetworkType(androidx.work.NetworkType.UNMETERED) // 设置设备网络类型,需要有网络且是wifi才能运行。默认值为 NetworkType.NOT_REQUIRED。
//                .setRequiresBatteryNotLow(true) // 如果设置为 true，那么当设备处于“电量不足模式”时，工作不会运行。
//                .setRequiresDeviceIdle(true) // 如果设置为 true，则要求用户的设备必须处于空闲状态，才能运行工作。在运行批量操作时，此约束会非常有用；若是不用此约束，批量操作可能会降低用户设备上正在积极运行的其他应用的性能。
//                .setRequiresStorageNotLow(true) // 如果设置为 true，那么当设备存储空间不足时，工作不会运行。)
//                .build()
//            val myWorkRequest = PeriodicWorkRequestBuilder<MyWorker2>(1, TimeUnit.MINUTES) // 一分钟执行一次
//                .setConstraints(constraints) // 添加工作约束
//                .setInitialDelay(10, TimeUnit.MINUTES) // 如果工作没有约束，或者当工作加入队列时所有约束都得到了满足，那么系统可能会选择立即运行该工作。如果您不希望工作立即运行，可以将工作指定为在经过一段最短初始延迟时间后再启动。将工作设置为在加入队列后至少经过 10 分钟后再运行。
//                .setBackoffCriteria(
//                    BackoffPolicy.LINEAR,
//                    MIN_BACKOFF_MILLIS,
//                    TimeUnit.MILLISECONDS) // 重试和退避政策,如果您需要让 WorkManager 重试工作，可以从工作器返回 Result.retry()。然后，系统将根据退避延迟时间和退避政策重新调度工作。
//                .addTag("cleanup") // 个工作请求都有一个唯一标识符，该标识符可用于在以后标识该工作，以便取消工作或观察其进度。WorkManager.cancelAllWorkByTag(String) 会取消带有特定标记的所有工作请求,WorkManager.getWorkInfosByTag(String) 会返回一个 WorkInfo 对象列表，该列表可用于确定当前工作状态
//                .setInputData(workDataOf(
//                    "IMAGE_URI" to "https://www.baidu.com/"
//                )) // 设置传递给work数据。
//                .build()
//            // 可以定义的最短重复间隔是 15 分钟（与 JobScheduler API 相同）。
            val myWorkRequest = PeriodicWorkRequestBuilder<MyWorker2>(16, TimeUnit.MINUTES) // 16分钟执行一次
                 .setInputData(workDataOf(
                    "IMAGE_URI" to "https://www.baidu.com/"
                ))
                .build()
            WorkManager.getInstance(this).enqueue(myWorkRequest) // 把WorkRequest放入队列，提交到 WorkManager。
        }

    }

    class MyWorker1(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext,workerParams) {
        override fun doWork(): Result {
            Log.i("Toaster", "MyWorker1被执行")
            return Result.success()
        }
    }

    class MyWorker2(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext,workerParams) {
        override fun doWork(): Result {
            val inputData = inputData.getString("IMAGE_URI")
            Log.i("Toaster", "MyWorker2被执行: $inputData")
            return Result.success()
        }
    }
}

