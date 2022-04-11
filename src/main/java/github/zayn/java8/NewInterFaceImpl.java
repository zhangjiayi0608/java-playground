package github.zayn.java8;

/**
 * @ClassName NewInterFaceImpl
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/9/13 11:36 上午
 **/
public class NewInterFaceImpl implements NewInterface,AnotherInterface{
    @Override
    public void anotherMethod() {

    }

    @Override
    public void otherMethod() {

    }

    @Override
    public void doSomething() {
        NewInterface.super.doSomething();
    }


    
}
