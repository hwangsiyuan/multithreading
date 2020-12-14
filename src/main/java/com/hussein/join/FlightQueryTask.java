package com.hussein.join;

import com.hussein.lock.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <p>Title: FlightQueryTask</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 2:44 PM
 */
public class FlightQueryTask extends Thread implements FlightQuery {

    private String from;

    private String to;

    private final List<String> flightList = new ArrayList<>();

    public FlightQueryTask(String airName, String from, String to) {
        super("[" + airName + "]");
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s\n", getName(), from, to);
        Tools.pause();
        flightList.add(getName() + "-" + new Random().nextInt(10));
        System.out.printf("%s-query from %s to %s is success.\n", getName(), from, to);
    }

    @Override
    public List<String> getFlightList() {
        return flightList;
    }

    public static void main(String[] args) {
        List<String> airNameList = new ArrayList<>(Arrays.asList("HN", "DF", "NF"));
        String from = "BEIJING";
        String to = "NANJING";
        List<FlightQueryTask> taskList = airNameList.stream().map(a -> new FlightQueryTask(a, from, to)).collect(Collectors.toList());
        taskList.forEach(Thread::start);
        taskList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        List<String> result = new ArrayList<>();
        taskList.stream().map(FlightQueryTask::getFlightList).forEach(result::addAll);
        System.out.println(result);
    }
}
