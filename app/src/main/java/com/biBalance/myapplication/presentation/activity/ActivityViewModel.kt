package com.biBalance.myapplication.presentation.activity

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
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
                isLoading = false,
                isActivityContentVisible = false
            )
        }
    }

    private fun getActivity() {
        Log.d("vefvefvefdvevevferve", "get")
        tryToExecute(
            { repository.getActivity(activityArgs.activityId.toInt()) },
            ::onGetActivitySuccess,
            ::onError
        )
    }

//    private fun onGetActivitySuccess(activity: Activity) {
//        updateState {
//            it.copy(
//                activityDescription = activity.activityDescription.map { it(Charset.forName("UTF-8") },
//                activityTitle = activity.activityTitle.toString(Charset.forName("UTF-8")),
//                isLoading = false
//            )
//        }
//        Log.d("vefvefvefdvevevferve", activity.toString())
//    }

    private fun onGetActivitySuccess(activity: Activity) {
        Log.d("vefvefvefdvevevferve", "success")
//        activity.description
//        activity.title
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

    private fun onError(error: ErrorHandler) {
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
            repository.storeResult(1,1)
        }
        sendEffect(ActivityUIEffect.GoToActivitiesScreen)
    }

    override fun onClickBack() {
        sendEffect(ActivityUIEffect.OnClickBack)
    }

}