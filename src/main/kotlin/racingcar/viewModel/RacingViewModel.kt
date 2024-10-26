package racingcar.viewModel

import racingcar.constans.Constants.SEPARATOR
import racingcar.delegate.ValidationDelegator
import racingcar.intent.UserInputIntent
import racingcar.intent.UserInputIntent.EnterPlayCountState
import racingcar.intent.UserInputIntent.EnterPlayerNameState
import racingcar.model.CarRacingState.PlayerState

class RacingViewModel(
    private val validationDelegator: ValidationDelegator
) {
    private val state = mutableListOf<PlayerState>()
    private var playCount: Int = 0

    fun onCompleteInput(intent: UserInputIntent){
        when(intent){
            is EnterPlayerNameState -> onCompleteInputPlayerNames(intent)
            is EnterPlayCountState -> onCompleteInputPlayCount(intent)
        }
    }

    private fun onCompleteInputPlayerNames(intent: EnterPlayerNameState){
        val userNames = intent.userNames
        validationDelegator.handleUserNameInput(userNames)
        val separatedNames = userNames
            .split(SEPARATOR)
            .map { it.trim() }
        readyForPlayers(separatedNames)
    }

    private fun onCompleteInputPlayCount(intent: EnterPlayCountState) {
        val playCount = intent.playCount
        validationDelegator.checkPlayCountIsValidNumeric(playCount)
        validationDelegator.handlePlayCountInput(playCount.toInt())
        readyForPlayCount(playCount.toInt())
    }

    private fun readyForPlayers(separatedNames: List<String>) {
        separatedNames.forEach { name ->
            state.add(PlayerState(playerName = name, position = 0))
        }
    }

    private fun readyForPlayCount(playCount: Int) {
        this.playCount = playCount
    }
}