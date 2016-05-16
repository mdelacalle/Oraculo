package mdelacalle.com.oraculo.model;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class Outcome {
    public int Ktotal;
    public int ball;
    public int intentionalBall;

    public int getKtotal() {
        return Ktotal;
    }

    public void setKtotal(int ktotal) {
        this.Ktotal = ktotal;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int getIntentionalBall() {
        return intentionalBall;
    }

    public void setIntentionalBall(int intentionalBall) {
        this.intentionalBall = intentionalBall;
    }
}
