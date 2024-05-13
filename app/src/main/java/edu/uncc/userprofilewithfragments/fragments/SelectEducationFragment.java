package edu.uncc.userprofilewithfragments.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.uncc.userprofilewithfragments.MainActivity;
import edu.uncc.userprofilewithfragments.databinding.FragmentSelectEducationBinding;

public class SelectEducationFragment extends Fragment {

    private static final String ARG_RESPONSE_PARAM = "response";
    private Response mResponse;
    private FragmentSelectEducationBinding binding;
    private EducationListener mListener;

    public interface EducationListener{
        void goToDemographicFragment(Response response);
        void cancelGoToDemographicFragment();
    }

    public SelectEducationFragment() {
        // Required empty public constructor
    }

    public static SelectEducationFragment newInstance(Response response) {
        SelectEducationFragment fragment = new SelectEducationFragment();
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
        binding = FragmentSelectEducationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (EducationListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(v -> {
            mListener.cancelGoToDemographicFragment();
        });

        binding.buttonSubmit.setOnClickListener(v -> {
            int checkedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
            if(checkedRadioButtonId == -1)
                Toast.makeText(getActivity(), "Select any education level", Toast.LENGTH_SHORT).show();
            else {
                RadioButton selectedButton = view.findViewById(checkedRadioButtonId);
                mResponse.setEducationLevel(selectedButton.getText().toString());
                Log.d(MainActivity.TAG, "From Select Education Fragment: "+mResponse);
                mListener.goToDemographicFragment(mResponse);
            }
        });
    }
}