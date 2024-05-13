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
import android.widget.SeekBar;

import edu.uncc.userprofilewithfragments.MainActivity;
import edu.uncc.userprofilewithfragments.R;
import edu.uncc.userprofilewithfragments.databinding.FragmentSelectIncomeBinding;
import edu.uncc.userprofilewithfragments.databinding.FragmentSelectLivingStatusBinding;

public class SelectIncomeFragment extends Fragment {
    private static final String ARG_RESPONSE_PARAM = "response";
    private Response mResponse;
    private FragmentSelectIncomeBinding binding;
    private IncomeListener mListener;
    String[] incomeRangeValues;

    public interface IncomeListener{
        void goToDemographicFragment(Response response);
        void cancelGoToDemographicFragment();
    }

    public SelectIncomeFragment() {
        // Required empty public constructor
    }

    public static SelectIncomeFragment newInstance(Response response) {
        SelectIncomeFragment fragment = new SelectIncomeFragment();
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
        binding = FragmentSelectIncomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (IncomeListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        incomeRangeValues = getResources().getStringArray(R.array.incomeRangeValues);

        binding.textViewHouseHoldIncome.setText(incomeRangeValues[0]);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textViewHouseHoldIncome.setText(incomeRangeValues[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.buttonCancel.setOnClickListener(v -> {
            mListener.cancelGoToDemographicFragment();
        });

        binding.buttonSubmit.setOnClickListener(v -> {
            mResponse.setIncomeRange(incomeRangeValues[binding.seekBar.getProgress()]);
            Log.d(MainActivity.TAG, "From Select Household Income Fragment: "+mResponse);
            mListener.goToDemographicFragment(mResponse);
        });
    }
}