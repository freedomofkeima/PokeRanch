/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 *
 * @author OkepLovers
 * Fathan Adi Pranaya / 13511027
 * Mohamad Rivai Ramandhani / 13511043
 * Yogi Salomo Mangontang Pratama / 13511059
 * Renusa Andra Prayogo / 13511063
 * Habibie Faried / 13511069
 * Iskandar Setiadi / 13511073
 * 
 */
public class HelpMenu extends Activity  implements ViewSwitcher.ViewFactory {

    private TextSwitcher mSwitcher; //TextSwitcher for changing TextView

    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help);

        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);

        Button nextButton = (Button) findViewById(R.id.next);
        Button prevButton = (Button) findViewById(R.id.prev);
        
        nextButton.setOnClickListener(new View.OnClickListener() {
        
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        if (mCounter < 20) mCounter++;
		           else mCounter = 0;
		        updateCounter();
			}
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        if (mCounter != 0) mCounter--;
		           else mCounter = 20;
		        updateCounter();
			}
        });
        
        updateCounter();
    }
    
    public void backtoMain(View v){
    	super.finish();
    }
    

    private void updateCounter() {
    	/* Help menu here */
    	if (mCounter == 0) mSwitcher.setText("\nWelcome to PokeRanch!");
    	if (mCounter == 1) mSwitcher.setText("\nClick ^ Button for moving Up");
    	if (mCounter == 2) mSwitcher.setText("\nClick < Button for moving Left");
    	if (mCounter == 3) mSwitcher.setText("\nClick > Button for moving Down");
    	if (mCounter == 4) mSwitcher.setText("\nClick v Button for moving Right");
    	if (mCounter == 5) mSwitcher.setText("\nClick <3 Button for menu");
    	if (mCounter == 6) mSwitcher.setText("\nClick X Button for interact with object");
    	if (mCounter == 7) mSwitcher.setText("\nClick Bobo Button for Sleep");
    	if (mCounter == 8) mSwitcher.setText("\nClick Transaksi Button for Starting Transaction");
    	if (mCounter == 9) mSwitcher.setText("\nClick Kombinasi Button for Combining pokemon");
    	if (mCounter == 10) mSwitcher.setText("\nClick Particiate Button for Choosing bet for turnament");
    	if (mCounter == 11) mSwitcher.setText("\nClick Mulai Yuk Button for Starting turnament");
    	if (mCounter == 12) mSwitcher.setText("\nSpecial Thanks to");
    	if (mCounter == 13) mSwitcher.setText("\nFathan Adi Pranaya");
    	if (mCounter == 14) mSwitcher.setText("\nMohamad Rivai Ramandhani");
    	if (mCounter == 15) mSwitcher.setText("\nYogi Salomo Mangontang Pratama");
    	if (mCounter == 16) mSwitcher.setText("\nRenusa Andra Prayogo");
    	if (mCounter == 17) mSwitcher.setText("\nHabibie Faried");
    	if (mCounter == 18) mSwitcher.setText("\nIskandar Setiadi");
    	if (mCounter == 19) mSwitcher.setText("\nThis program is brought to you by:");
    	if (mCounter == 20) mSwitcher.setText("\nFiesta, karena rasa adalah segalanya");
    	
    }

    public View makeView() {
        TextView textView = new TextView(this);
    	//Output TextView at the Screen
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(30);
        return textView;
    }
}