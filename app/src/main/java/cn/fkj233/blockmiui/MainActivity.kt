package cn.fkj233.blockmiui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.view.SpinnerV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.OnRequestPermissionResultListener

class MainActivity : MIUIActivity(){
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    private val activity = this

    init {
        initView {
            shizuku.add_req_listener()
            registerMain(getString(R.string.info), false) {
                TextSummaryWithSpinner(
                    TextSummaryV("墓碑模式", tips = "暂停执行已缓存的应用"),
                    SpinnerV("默认值") {
                        add("默认值") { runCode("default") }
                        add("启用") { runCode("enable")}
                        add("禁用") { runCode("disabled") }
                    })

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setSP(getPreferences(0))
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        if (shizuku.check_permission() != 0){
            shizuku.apply_shizuku(1000)
        }

        super.onResume()
    }

    private fun showToast(string: String) {
        handler.post {
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }
    }

    fun showDialog(title: String = "注意", mag: String = "设置后需要重启生效，点击确定即可重启"){
        MIUIDialog(activity) {
            setTitle(title)
            setMessage(mag)
            setLButton("关闭") {
                dismiss()
            }
            setRButton("确定") {
                ShizukuExecUtils.ShizukuExec("reboot")
            }
        }.show()
    }

    fun runCode(freezer_status:String){
        ShizukuExecUtils.ShizukuExec("settings put global cached_apps_freezer ${freezer_status}")
        showDialog()
    }

}