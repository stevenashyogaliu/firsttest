package com.example.demo1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.sqlite2.DBHelper;
import com.example.sqlite2.Srlb;
import com.example.sqlite4.DBHelper2;
import com.example.sqlite4.Sr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ThirdActivity extends Activity{
     private Button btn1,btn2;
	 private EditText et1,et2;
	 private Spinner sp;
	 DBHelper2 db;
	 private String[] str;
	 private String TypeResult;
	 private TextView in_manage_date;
	 private ArrayList<String> arraylist = new ArrayList<String>();
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test17);
		PublicWay.activityList.add(this);
		in_manage_date = (TextView)findViewById(R.id.dateview);
		btn1=(Button)findViewById(R.id.login);
		btn2=(Button)findViewById(R.id.quit);
		et1=(EditText)findViewById(R.id.srje);
		et2=(EditText)findViewById(R.id.bz);
		sp=(Spinner) findViewById(R.id.spinner);
		if(in_manage_date.getText().equals("")){
	    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	//获取当前时间
	    	Date date = new Date(System.currentTimeMillis());
	    	in_manage_date.setText(simpleDateFormat.format(date));
	     }
		
		ArrayAdapter<String> adapter2;
		int Size =new DBHelper(ThirdActivity.this).Size();
		List<Srlb> list=new DBHelper(ThirdActivity.this).find();
		for(int i=0;i<Size;i++) {
			arraylist.add(list.get(i).getsrmc());
		}
		str = arraylist.toArray(new String[0]);
	        adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str);
	        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp.setAdapter(adapter2);
	        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        	@Override
	        	public void onItemSelected(AdapterView<?> parent,View arg1,int pos,long id){
	        		TypeResult = parent.getItemAtPosition(pos).toString();
	        		
	        	}
	        	@Override
	        	public void onNothingSelected(AdapterView<?> arg0){
	        		
	        	}
			});

	        in_manage_date.setOnClickListener(new OnClickListener() {
		           @Override
		           public void onClick(View v) {
		               AlertDialog.Builder localBuilder = new AlertDialog.Builder(ThirdActivity.this);
		               localBuilder.setTitle("选择时间");
		               //
		               final LinearLayout layout_alert= (LinearLayout) getLayoutInflater().inflate(R.layout.test28, null);
		               localBuilder.setView(layout_alert);
		               localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		               {
		                   public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
		                   {
		                       DatePicker datepicker1= (DatePicker) layout_alert.findViewById(R.id.in_datepicker);
		                       int y=datepicker1.getYear();
		                       int m=datepicker1.getMonth()+1;
		                       int d=datepicker1.getDayOfMonth();
		                       System.out.println("y:"+y+" m:"+m+" d:"+d);
		                       in_manage_date.setText(y+"-"+m+"-"+d); //  获取时间
		                   }
		               }).setNegativeButton("取消", new DialogInterface.OnClickListener()
		               {
		                   public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
		                   {
		 
		                   }
		               }).create().show();
		           }
		       });
		btn1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
            	String je = et1.getText()+"";
            	if(je.equals(null) || je ==""||je==null){
            		Toast.makeText(ThirdActivity.this, "收入金额不能为空", Toast.LENGTH_SHORT).show();
            	}else{
            		Sr info=new Sr();
                    DBHelper2 helper=new DBHelper2(ThirdActivity.this);
                    info.setDate(in_manage_date.getText().toString());
                    info.setSrlb(TypeResult);
                    info.setSrje(et1.getText().toString());
                    info.setBz(et2.getText().toString());
                    helper.insert(info);
                    et1.setText("");
                    et2.setText("");
                    Toast.makeText(ThirdActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
            	}
                
            }
        });
		btn2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
            	Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
				startActivity(intent);
            }});
	}
	}