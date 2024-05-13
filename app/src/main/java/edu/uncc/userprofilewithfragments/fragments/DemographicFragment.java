package edu.uncc.userprofilewithfragments.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.uncc.userprofilewithfragments.MainActivity;
import edu.uncc.userprofilewithfragments.databinding.FragmentDemographicBinding;

public class DemographicFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_RESPONSE_PARAM = "response";
    private Response mResponse;
    private FragmentDemographicBinding binding;
    private DemographicListener mListener;

    public void setResponse(Response response) {
        this.mResponse = response;
    }

    public interface DemographicListener{
        void startSelectEducationFragment(Response mResponse);
        void startSelectMaritalStatusFragment(Response mResponse);
        void startSelectLivingStatusFragment(Response mResponse);
        void startSelectIncomeFragment(Response mResponse);
        void startProfileFragment(Response response);
    }

    public DemographicFragment() {
        // Required empty public constructor
    }
    public static DemographicFragment newInstance(Response response) {
        DemographicFragment fragment = new DemographicFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESPONSE_PARAM, response);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mResponse = (Response) getArguments().getSerializable(ARG_RESPONSE_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDemographicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DemographicListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mResponse.getEducationLevel() != null)
            binding.textViewEducation.setText(mResponse.getEducationLevel());
        if(mResponse.getMaritalStatus() != null)
            binding.textViewMaritalStatus.setText(mResponse.getMaritalStatus());
        if(mResponse.getLivingStatus() != null)
            binding.textViewLivingStatus.setText(mResponse.getLivingStatus());
        if(mResponse.getIncomeRange() != null)
            binding.textViewIncomeStatus.setText(mResponse.getIncomeRange());

        binding.buttonSelectEducation.setOnClickListener(this);
        binding.buttonSelectMarital.setOnClickListener(this);
        binding.buttonSelectLiving.setOnClickListener(this);
        binding.buttonSelectIncome.setOnClickListener(this);
        binding.buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonSelectEducation.getId())
            mListener.startSelectEducationFragment(mResponse);
        else if (v.getId() == binding.buttonSelectMarital.getId())
            mListener.startSelectMaritalStatusFragment(mResponse);
        else if (v.getId() == binding.buttonSelectLiving.getId())
            mListener.startSelectLivingStatusFragment(mResponse);
        else if (v.getId() == binding.buttonSelectIncome.getId())
            mListener.startSelectIncomeFragment(mResponse);
        if(v.getId() == binding.buttonNext.getId()) {
            String education = binding.textViewEducation.getText().toString();
            String maritalStatus = binding.textViewMaritalStatus.getText().toString();
            String livingStatus = binding.textViewLivingStatus.getText().toString();
            String income = binding.textViewIncomeStatus.getText().toString();
            Log.d(MainActivity.TAG, "onClick: ");
            if(education.equals("N/A") || maritalStatus.equals("N/A") || livingStatus.equals("N/A") || income.equals("N/A")){
                Toast.makeText(getActivity(), "Please select all the demographic options", Toast.LENGTH_SHORT).show();
            }
            else {
                mResponse.setEducationLevel(education);
                mResponse.setMaritalStatus(maritalStatus);
                mResponse.setLivingStatus(livingStatus);
                mResponse.setIncomeRange(income);
                Log.d(MainActivity.TAG, "From Demographic Fragment: "+mResponse);
                mListener.startProfileFragment(mResponse);
            }
        }
    }
}