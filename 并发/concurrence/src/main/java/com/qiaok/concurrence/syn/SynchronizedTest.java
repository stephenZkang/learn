package com.qiaok.concurrence.syn;

/**
 * synchronized实现机制
 *
 * @since 2020-05-07
 * @author qiaok
 */
public class SynchronizedTest {
    /**
     * 同步方法
     */
    public synchronized void  test(){

    }

    /**
     * 同步代码块
     */
    public void test2(){
        synchronized(this){

        }
    }
}
