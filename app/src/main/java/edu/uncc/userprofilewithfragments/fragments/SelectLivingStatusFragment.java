package edu.uncc.userprofilewithfragments.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.uncc.userprofilewithfragments.MainActivity;
import edu.uncc.userprofilewithfragments.R;
import edu.uncc.userprofilewithfragments.databinding.FragmentSelectLivingStatusBinding;
import edu.uncc.userprofilewithfragments.databinding.FragmentSelectMaritalStatusBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectLivingStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectLivingStatusFragment extends Fragment {

    private static final String ARG_RESPONSE_PARAM = "response";
    private Response mResponse;
    private FragmentSelectLivingStatusBinding binding;
    private LivingStatusListener mListener;

    public interface LivingStatusListener{
        void goToDemographicFragment(Response response);
        void cancelGoToDemographicFragment();
    }

    public SelectLivingStatusFragment() {
        // Required empty public constructor
    }

    public static SelectLivingStatusFragment newInstance(Response response) {
        SelectLivingStatusFragment fragment = new SelectLivingStatusFragment();
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
        binding = FragmentSelectLivingStatusBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (LivingStatusListener) context;
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
                Toast.makeText(getActivity(), "Select any living status", Toast.LENGTH_SHORT).show();
            else {
                RadioButton selectedButton = view.findViewById(checkedRadioButtonId);
                mResponse.setLivingStatus(selectedButton.getText().toString());
                Log.d(MainActivity.TAG, "From Select Living Status Fragment: " + mResponse);
                mListener.goToDemographicFragment(mResponse);
            }
        });
    }
}