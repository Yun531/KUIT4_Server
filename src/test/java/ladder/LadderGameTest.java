package ladder;

import ladder.creator.LadderAutoCreator;
import ladder.creator.LadderCreator;
import ladder.creator.LadderSelfCreator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LadderGameTest {

    @Test
    void 사다리_생성_확인() {
        //given
        GreaterThanOne numberOfRow = GreaterThanOne.from(3);
        GreaterThanOne numberOfPerson = GreaterThanOne.from(5);

        //when
        LadderAutoCreator ladderAutoCreator = new LadderAutoCreator(numberOfRow, numberOfPerson);

        //then
        assertThat(ladderAutoCreator).isNotNull();
    }

    @Test
    void 사다리_사람_예외_처리_확인() {
        //when
        GreaterThanOne numberOfRow = GreaterThanOne.from(2);
        GreaterThanOne numberOfPerson = GreaterThanOne.from(3);

        LadderAutoCreator ladderAutoCreator = new LadderAutoCreator(numberOfRow, numberOfPerson);
        LadderGame ladderGame = new LadderGame(ladderAutoCreator);

        //given
        Position position = Position.from(3);

        //then
        assertThatThrownBy(() -> ladderGame.run(position))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void 사다리_사람_예외_처리_확인2() {
        //when
        GreaterThanOne numberOfRow = GreaterThanOne.from(2);
        GreaterThanOne numberOfPerson = GreaterThanOne.from(3);

        LadderAutoCreator ladderAutoCreator = new LadderAutoCreator(numberOfRow, numberOfPerson);
        LadderGame ladderGame = new LadderGame(ladderAutoCreator);

        //given
        Position position = Position.from(4);

        //then
        assertThatThrownBy(() -> ladderGame.run(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사다리_결과_확인() {
        //when
        GreaterThanOne numberOfPerson = GreaterThanOne.from(4);
        GreaterThanOne row = GreaterThanOne.from(3);
        LadderSelfCreator ladderSelfCreator = new LadderSelfCreator(row, numberOfPerson);
        LadderGame ladderGame = new LadderGame(ladderSelfCreator);

        ladderSelfCreator.drawLine(Position.from(0),Position.from(0));
        ladderSelfCreator.drawLine(Position.from(1),Position.from(1));
        ladderSelfCreator.drawLine(Position.from(2),Position.from(0));


        //given
        Position position = Position.from(0);

        //then
        assertThat(ladderGame.run(position)).isEqualTo(2);

        //given
        position = Position.from(1);

        //then
        assertThat(ladderGame.run(position)).isEqualTo(1);

        //given
        position = Position.from(2);

        //then
        assertThat(ladderGame.run(position)).isEqualTo(0);
    }
}