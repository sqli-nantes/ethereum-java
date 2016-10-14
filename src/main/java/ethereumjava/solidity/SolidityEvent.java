package ethereumjava.solidity;

import ethereumjava.module.Eth;
import ethereumjava.solidity.types.SType;
import rx.Observable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gunicolas on 4/08/16.
 */
public class SolidityEvent extends SolidityElement{


    class SolidityEventEncoded {
        List<String> topics;
        String address;

        public SolidityEventEncoded(List<String> topics, String address) {
            this.topics = topics;
            this.address = address;
        }
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Anonymous{
        boolean value();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Parameters{
        Parameter[] value();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Parameter{
        boolean indexed();
        String name();
        Class<? extends SType> type();
    }

    public SolidityEvent(String address,Method method,Eth eth) {
        super(address,method,eth);
    }

    @Override
    protected Class[] getParametersTypes() {

        List<Class> ret = new ArrayList<>();

        Parameters parameters = method.getAnnotation(Parameters.class);

        if( parameters != null ){
            for( Parameter parameter : parameters.value()){
                ret.add(parameter.type());
            }
        }

        return ret.toArray(new Class[ret.size()]);
    }

    public void encode(){

        List<String> topics = new ArrayList<>();
        topics.add("0x"+this.signature());

        //new SolidityEventEncoded(,this.address);
    }


    public Observable watch(){

        encode();





        return null;
    }
}
