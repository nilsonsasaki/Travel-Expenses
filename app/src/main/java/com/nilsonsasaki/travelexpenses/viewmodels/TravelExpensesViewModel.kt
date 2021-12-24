package com.nilsonsasaki.travelexpenses.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nilsonsasaki.travelexpenses.R
import com.nilsonsasaki.travelexpenses.models.TravelValues

enum class ErrorStates { NULL, NEGATIVE, UNNESPECTED }

class TravelExpensesViewModel : ViewModel() {

    val toastMessage = MutableLiveData<Int>()
    val travelValues: MutableLiveData<TravelValues> = MutableLiveData<TravelValues>()
    private var errorState: ErrorStates? = null

    fun calculateExpenses(values: TravelValues) {

        val result: Double
        try {
            validateValues(values)
            if (errorState == null) {
                result =
                    (values.distance.toDouble() * values.gasPrice.toDouble()) / values.autonomy.toDouble()

                travelValues.value =
                    TravelValues(
                        values.distance,
                        values.gasPrice,
                        values.autonomy,
                        String.format("%.2f", result)
                    )
            } else {
                validationToast(errorState!!)
            }

        } catch (e: Exception) {

            validationToast(ErrorStates.UNNESPECTED)

        } finally {
            errorState = null
        }
    }

    private fun validateValues(values: TravelValues) {
        if (checkNullOrBlank(values)) {
            checkNegativeOrZeroValues(values)
        }
    }

    private fun checkNegativeOrZeroValues(values: TravelValues): Boolean {
        if (values.distance.toDouble() <= 0 || values.gasPrice.toDouble() <= 0 || values.autonomy.toDouble() <= 0) {
            errorState = ErrorStates.NEGATIVE
            return false
        }
        return true
    }

    private fun checkNullOrBlank(values: TravelValues): Boolean {
        if (values.distance.isBlank() || values.autonomy.isBlank() || values.gasPrice.isBlank()) {
            errorState = ErrorStates.NULL
            return false
        }
        return true
    }

    private fun validationToast(error: ErrorStates) {
        when (error) {
            ErrorStates.NULL -> toastMessage.value = R.string.pt_null_value
            ErrorStates.NEGATIVE -> toastMessage.value = R.string.pt_negative_value
            ErrorStates.UNNESPECTED -> toastMessage.value = R.string.pt_unespected_error
        }
    }
}