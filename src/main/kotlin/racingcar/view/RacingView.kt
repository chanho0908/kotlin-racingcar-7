package racingcar.view

import camp.nextstep.edu.missionutils.Console.close
import camp.nextstep.edu.missionutils.Console.readLine
import racingcar.constans.Constants.MESSAGE_GUIDE_FOR_INPUT_COUNT
import racingcar.constans.Constants.MESSAGE_GUIDE_FOR_USER_NAME
import racingcar.di.DependencyInjector
import racingcar.intent.UserInputIntent.EnterPlayCountState
import racingcar.intent.UserInputIntent.EnterPlayerNameState
import racingcar.model.CarRacingState.PlayResultState

class RacingView(
    dependencyInjector: DependencyInjector
){
    private val viewModel = dependencyInjector.injectViewModel()

    init {
        startGame()
        val result = viewModel.onCompleteValidationCheck()
    }

    private fun startGame(){
        inputPlayerNames()
        inputPlayCount()
    }

    private fun inputPlayerNames() {
        val userNames = getUserInput(MESSAGE_GUIDE_FOR_USER_NAME)
        val intent = EnterPlayerNameState(userNames)
        viewModel.onCompleteInput(intent)
    }

    private fun inputPlayCount() {
        val playCount = getUserInput(MESSAGE_GUIDE_FOR_INPUT_COUNT)
        val intent = EnterPlayCountState(playCount)
        viewModel.onCompleteInput(intent)
    }

    private fun getUserInput(msg: String): String{
        println(msg)
        return readLine().trim()
    }

}