package vn.easycare.layers.ui.components.adapters;

import android.app.Activity;
import android.content.Context;
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
import vn.easycare.utils.AppFnUtils;

/**
 * Created by ThuNguyen on 12/16/2014.
 */
public class PatientListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean mIsBlackList = false;
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
            viewHolder.mButtonLayout = convertView.findViewById(R.id.patientListButtonLayout);
            viewHolder.mBtnBlock = (Button) convertView.findViewById(R.id.btnBlock);
            viewHolder.mBtnDating = (Button) convertView.findViewById(R.id.btnDating);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mIsBlackList){
            viewHolder.mButtonLayout.setVisibility(View.GONE);
        }else{
            viewHolder.mButtonLayout.setVisibility(View.VISIBLE);
        }
        // Set on click
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
        return convertView;
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnBlock:
                    Toast.makeText(mContext, "Block clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnDating:
                    Toast.makeText(mContext, "Dating clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private static class ViewHolder{
        private NetworkImageView mPatientAvatar;
        private TextView mPatientName;
        private TextView mPatientPhone;
        private TextView mPatientEmail;
        private View mButtonLayout;
        private Button mBtnBlock;
        private Button mBtnDating;
    }
}
