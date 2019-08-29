package org.smart4j.chapter6.threadlocal;

/**
 * Created by creasypita on 8/29/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class ClientThread extends Thread{

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(currentThread().getName() + " => " + sequence.getNumber());
        }
    }
}
