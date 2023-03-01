package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @DisplayName("이름은 5글자 이하이다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ash", "kiara", "woowa"})
    void validateNameLength(String name) {
        assertThatNoException().isThrownBy(() -> new Player(name));
    }

    @DisplayName("이름이 5글자 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"hijava", "helloworld", "woowacourse"})
    void nameNotOver5(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void nameNotEmpty(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 점수가 21보다 작으면 카드를 받을 수 있다")
    @Test
    void receiveCard_WhenScoreUnder21() {
        Player player = new Player("kiara");
        player.receiveCard(new Card(Denomination.SIX, Suits.HEART));
        assertThat(player.isReceivable()).isTrue();
    }

    @DisplayName("카드 점수가 21 이상이면 카드를 받을 수 없다")
    @Test
    void notReceiveCard_WhenScoreOver21() {
        Player player = new Player("kiara");
        player.receiveCard(new Card(Denomination.JACK, Suits.HEART));
        player.receiveCard(new Card(Denomination.FIVE, Suits.HEART));
        player.receiveCard(new Card(Denomination.SIX, Suits.HEART));
        assertThat(player.isReceivable()).isFalse();
    }
}
