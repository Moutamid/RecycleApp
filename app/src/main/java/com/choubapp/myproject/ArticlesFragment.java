package com.choubapp.myproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class ArticlesFragment extends Fragment {

    ImageView logout_btn;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        WebView webView = view.findViewById(R.id.webviewfeed);
        webView.loadUrl("https://www.nea.gov.sg/media");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        logout_btn = view.findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!= null)
                    mAuth.signOut();
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;

    }
}