package com.biBalance.myapplication.presentation.activity

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: BiBalanceRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ActivityUIState, ActivityUIEffect>(ActivityUIState()),
    ActivityInteractionListener {

    private val activityArgs = ActivityArgs(savedStateHandle)

    init {
        updateState { it.copy(activityStateId = activityArgs.activityId.toInt()) }
        getActivity()
//        viewModelScope.launch {
//            repository.getActivit(activityArgs.activityId.toInt())
//        }
    }

    private fun getActivity() {
        tryToExecute(
            { repository.getActivity(activityArgs.activityId.toInt()) },
            ::onGetActivitySuccess,
            ::onError
        )
    }

//    private fun onGetActivitySuccess(activity: Activity) {
//        updateState {
//            it.copy(
//                activityDescription = activity.activityDescription,
//                activityTitle = activity.activityTitle,
//                isLoading = false
//            )
//        }
//        Log.d("vefvefvefdvevevferve", activity.toString())
//    }

    private fun onGetActivitySuccess(activity: Activity) {
        Log.d("vefvefvefdvevevferve", "success")
//        activity.description
//        activity.title
        when(activityArgs.activityId.toInt()){
            1 -> {
                updateState {
                    it.copy(
                        activityDescription = listOf(
                            "ممارسة اليوجا يمكن أن تجعلك تشعر بالتحسن وتقلل من القلق، بالإضافة إلى ذلك فهو يحسن الجهاز الهضمي ويجلب المرونة للجسم ويقوي العظام",
                            "قم بالجلوس مع فرد الساقين إلى الأمام ، ضم أصابع القدمين والكعبين الي بعضعما",
                            "اثني الجذع من الخصر،ضع راحتي اليد علي الاصابع الاربعة مع اصابع القدم",
                            "حفاظ على استقامة الركبتين، والانحناء باتجاه الركبتين. ضع الأنف بين الركبتين",
                            "إبقاء المرفقين الي الايفل مع سحب أصابع القدمين إلى الداخل باليدين",
                            "قم بالتنفس و الزفير بشكل طبيعي اثناء الانحناء",
                            "حافظ علي هذه الوضعية لبضعة ثوانٍ"
                        ),
                        activityTitle = "سنقوم بعمل تمرين اليوجا اليوم",
                        isLoading = false
                    )
                }
            }
            2 -> {
                updateState {
                    it.copy(
                        activityDescription = listOf(
                            "التأمل يفيد في تعزيز التنظيم العاطفي وتقليل التوتر",
                            " مما يساهم في تحسين استقرار المزاج",
                            " ابحث عن مكان مريح وهادئ للجلوس أو الاستلقاء",
                            " اجلس متربعا على الأرض أو على كرسي مع وضع قدميك بشكل مسطح على الأرض",
                            " حافظ على استقامة ظهرك ووضع يديك على ركبتيك",
                            " أغمض عينيك أو أبقها مفتوحة قليلاً بنظرة ناعمة",
                            " خذ أنفاسًا عميقة قليلة للمساعدة على استرخاء جسمك والاستقرار في اللحظة الحالية",
                            " وجه انتباهك إلى أنفاسك. لاحظ الإحساس بكل شهيق وزفير. يمكنك التركيز على النفس أثناء دخوله وخروجه من أنفك",
                            " أو الشعور بصعود وهبوط صدرك أو بطنك",
                            " عندما تركز على أنفاسك، من المرجح أن تنشأ الأفكار",
                            " اعترف بها دون إصدار أحكام وأعد انتباهك بلطف إلى أنفاسك. كن حاضرا في هذه اللحظة ومراقبة كل نفس",
                            " عندما تركز على انفاسك من المرجح ان تنشأ الافكار اعترف بها دون اصدار احكام واعد انتباهك بلطف الى انفاسك وكن حاضرا في هذه اللحظه ومراقبة كل نفس"
                        ),
                        activityTitle = "سنبدأ بتمرين التأمل اليوم",
                        isLoading = false
                    )
                }
            }
            3 -> {
                updateState {
                    it.copy(
                        activityDescription = listOf(
                            "يساعد في التنمية العقلية الإيجابية وتعزيز الرفاهية وتعزيز المرونة",
                            " مما يساعد على مواجهة أنماط التفكير السلبية وتحسين الصحة العقلية بشكل عام"
                        ),
                        activityTitle = "تمرين الامتنان",
                        isLoading = false
                    )
                }
            }
            else -> {
                updateState {
                    it.copy(
                        activityDescription = listOf(
                            "انضم الى مجتمعنا عبر تطبيق ديسكورد",
                            " حيث ستجد العديد من لاشخاص الذين يواجهون نفس المشكله هناك",
                            " يمكنك تبادل تجاربك فى الرحله العلاجيه والتشجيع المتبادل بينكم"
                        ),
                        activityTitle = "مجتمعنا",
                        isLoading = false
                    )
                }
            }
        }

    }

    private fun onError(error: Exception) {
        Log.d("vefvefvefdvevevferve", error.toString())
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error
            )
        }
    }

    override fun showActivityContent() {
        _state.update { it.copy(isActivityContentVisible = true,isStartScreenVisible = false) }
    }

    override fun onClickNextActivity() {
//        _state.update { it.copy(isStartScreenVisible = true) }
    }

    override fun showFinishScreen() {
        updateState { it.copy(isFinishScreenVisible = true, isActivityContentVisible = false) }
    }

    override fun onClickNextFinishScreen() {
        viewModelScope.launch {
            repository.storeResult( activityArgs.activityId.toInt(),1)
        }
        sendEffect(ActivityUIEffect.GoToActivitiesScreen)
    }

    override fun onClickBack() {
        sendEffect(ActivityUIEffect.OnClickBack)
    }

}