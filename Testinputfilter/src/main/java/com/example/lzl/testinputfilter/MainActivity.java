package com.example.lzl.testinputfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
  private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.test);
        editText.setFilters(new InputFilter[]{passportFilter,emjorFilter,lengthFilter}
       );
    }

    //护照过滤
    public InputFilter passportFilter = new InputFilter() {
        String characters = "[^a-zA-Z ]";
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {

            if( Pattern.compile(characters).matcher(source).find()){

                Toast.makeText(MainActivity.this,"非护照",Toast.LENGTH_LONG).show();
                return "";
            }

//            return source == null ? null : source.toString().replaceAll(characters, "");
            return source;
        }
    };


    // 过滤输入法表情
    public  InputFilter emjorFilter = new InputFilter() {

        Pattern emoji = Pattern
                .compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart,int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                Toast.makeText(MainActivity.this,"禁止输入表情",Toast.LENGTH_LONG).show();
                return "";
            }
            return source;
        }
    };

    public  InputFilter.LengthFilter lengthFilter=new InputFilter.LengthFilter(10){

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(dstart>9){
                    Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();
                }
            return super.filter(source, start, end, dest, dstart, dend);
        }
    };
}
