//2017318:watanabe
package com.example.madlax.touchex;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/**
 * Created by madlax on 2017/02/17.
 */

public class TouchEx extends Activity{
    //起動処理
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new TouchView(this));
    }
}
