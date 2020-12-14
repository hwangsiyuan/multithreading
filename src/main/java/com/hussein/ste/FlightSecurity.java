package com.hussein.ste;

import com.hussein.lock.Tools;

/**
 * <p>Title: FlightSecurity</p>
 * <p>Description: single thread executor</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 3:51 PM
 */
public class FlightSecurity {

    private int count;

    private String boardingPass = "null";

    private String idCard = "null";

    public void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        count++;
        Tools.pause();
        check();
    }

    private void check() {
        if (boardingPass.indexOf(0) != idCard.indexOf(0)) {
            throw new RuntimeException("======RuntimeException=======" + this);
        }
    }

    @Override
    public String toString() {
        return "The" + count + "passengers," +
                "boardingPass['" + boardingPass + "]" +
                ", idCard['" + idCard + "]";
    }

    static class Passenger extends Thread{

        FlightSecurity fs;

        private String boardingPass;

        private String idCard;

        public Passenger(FlightSecurity fs, String boardingPass, String idCard) {
            this.fs = fs;
            this.boardingPass = boardingPass;
            this.idCard = idCard;
        }

        @Override
        public void run() {
            while (true) {
                fs.pass(boardingPass, idCard);
            }
        }
    }

    public static void main(String[] args) {
        FlightSecurity fs = new FlightSecurity();
        new Passenger(fs,"A100002230","A22202232323").start();
        new Passenger(fs,"B100002230","B22202232323").start();
        new Passenger(fs,"C100002230","C22202232323").start();
    }
}
