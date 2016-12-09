
package com.aaronps.irmodule.yofengdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aaronps.irmodule.IRModuleYofeng;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    Button mConnectButton;
    Button mCode40Button;
    Button mCode41Button;

    IRModuleYofeng mIRModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIRModule = new IRModuleYofeng(this);

        mConnectButton = (Button)findViewById(R.id.button_connect);
        mCode40Button = (Button)findViewById(R.id.button_code40);
        mCode41Button = (Button)findViewById(R.id.button_code41);

        mConnectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tryOpen();
            }
        });

        mCode40Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendCode40();
            }
        });

        mCode41Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendCode41();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // why did I comment this?
//        tryOpen();

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        doClose();
    }

    void tryOpen()
    {
        Log.d(TAG, "tryOpen: will open");
        if ( mIRModule.open() )
            Log.d(TAG, "tryOpen: did open");
        else
            Log.d(TAG, "tryOpen: sorry, try again");
    }

    void doClose()
    {
        mIRModule.close();
    }

    void sendCode40()
    {
        Log.d(TAG, "sendCode40: ");
        mIRModule.write(0x40);
    }

    void sendCode41()
    {
        Log.d(TAG, "sendCode41: ");
        mIRModule.write(0x41);
    }
}
