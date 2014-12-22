package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import vn.easycare.R;
import vn.easycare.layers.ui.activities.HomeActivity;
import vn.easycare.layers.ui.fragments.PatientDetailFragment;
import vn.easycare.utils.AppFnUtils;
import vn.easycare.utils.FontUtil;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsBlackList = false;
    private boolean mIsClicked = false;
    public PatientListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setBlackList(boolean isBlackList){
        mIsBlackList = isBlackList;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.patient_item_ctrl, null);
            viewHolder = new ViewHolder();
            viewHolder.mPatientAvatar = (NetworkImageView) convertView.findViewById(R.id.patientAvatar);
            viewHolder.mPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
            viewHolder.mPatientPhone = (TextView) convertView.findViewById(R.id.tvPatientPhone);
            viewHolder.mPatientEmail = (TextView) convertView.findViewById(R.id.tvPatientEmail);
            viewHolder.mBtnBlock = (Button) convertView.findViewById(R.id.btnBlock);
            viewHolder.mBtnDating = (Button) convertView.findViewById(R.id.btnDating);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mIsBlackList){
            viewHolder.mBtnDating.setVisibility(View.GONE);
            viewHolder.mBtnBlock.setText(R.string.patient_list_unblock);
        }else{
            viewHolder.mBtnDating.setVisibility(View.VISIBLE);
            viewHolder.mBtnBlock.setText(R.string.patient_list_block);
        }
        // Set on click
        viewHolder.mPatientName.setTag(position);
        viewHolder.mPatientName.setOnClickListener(mOnClickListener);
        viewHolder.mBtnBlock.setTag(position);
        viewHolder.mBtnBlock.setOnClickListener(mOnClickListener);
        viewHolder.mBtnDating.setTag(position);
        viewHolder.mBtnDating.setOnClickListener(mOnClickListener);

        // Calculate the avatar size
        int screenWidth = AppFnUtils.getScreenWidth((Activity)mContext);
        int avatarSize = screenWidth / 5;
        viewHolder.mPatientAvatar.getLayoutParams().width = avatarSize;
        viewHolder.mPatientAvatar.getLayoutParams().height = avatarSize;

        // Set fake data
        viewHolder.mPatientAvatar.setDefaultImageResId(R.drawable.ic_no_avatar);
        viewHolder.mPatientName.setText("Nguyen Van A");
        viewHolder.mPatientPhone.setText("00388030330");
        viewHolder.mPatientEmail.setText("abcd@gmail.com");

        // Apply font
        AppFnUtils.applyFontForTextViewChild(convertView, null);
        return convertView;
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mIsClicked){
                return;
            }
            mIsClicked = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsClicked = false;
                }
            }, 500);
            switch (v.getId()){
                case R.id.btnBlock:
                    Toast.makeText(mContext, "Block clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnDating:
                    Toast.makeText(mContext, "Dating clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tvPatientName:
                    // Go to patient_detail screen
                    PatientDetailFragment patientDetailFragment = new PatientDetailFragment();
                    ((HomeActivity) mContext).showFragment(patientDetailFragment);
                    break;
            }
        }
    };
    private static class ViewHolder{
        private NetworkImageView mPatientAvatar;
        private TextView mPatientName;
        private TextView mPatientPhone;
        private TextView mPatientEmail;
        private Button mBtnBlock;
        private Button mBtnDating;
    }
}
