package com.example.ouresic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private static final int RC_SIGN_IN =1000 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Button btnext;
        btnext = findViewById(R.id.btnext);


         btnext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(firebaseUser!=null) {
                     Intent i = new Intent(getBaseContext(), LogActivity.class);
                     startActivity(i);
                 }
                 else{
                     startActivityForResult(
                             AuthUI.getInstance()
                                     .createSignInIntentBuilder()
                                     .setIsSmartLockEnabled(false)
                                     .setAvailableProviders(Arrays.asList(
                                             new AuthUI.IdpConfig.GoogleBuilder().build(),
                                             new AuthUI.IdpConfig.PhoneBuilder().build()))
                                     .build(),
                             RC_SIGN_IN);
                 }
             }
         });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Intent i = new Intent(getBaseContext(), LogActivity.class);
                startActivity(i);
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    return;
                }

            }
        }
    }

}
