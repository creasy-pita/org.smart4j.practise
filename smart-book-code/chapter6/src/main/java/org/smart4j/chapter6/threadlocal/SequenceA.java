package org.smart4j.chapter6.threadlocal;

/**
 * Created by creasypita on 8/29/2019.
 *
 * @ProjectName: lujunqiang-smart-book-code-master
 */
public class SequenceA implements Sequence {
    private static ThreadLocal<Integer> NUMBER_CONTAINER = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public int getNumber() {
        NUMBER_CONTAINER.set(NUMBER_CONTAINER.get()+1);
        return NUMBER_CONTAINER.get();
    }

    
    public static void main(String[] args){
        ClientThread thread1 = new ClientThread(new SequenceA());
        ClientThread thread2 = new ClientThread(new SequenceA());
        ClientThread thread3 = new ClientThread(new SequenceA());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
