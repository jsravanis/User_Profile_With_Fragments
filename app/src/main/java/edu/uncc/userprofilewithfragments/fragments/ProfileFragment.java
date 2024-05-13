package edu.uncc.userprofilewithfragments.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.userprofilewithfragments.R;
import edu.uncc.userprofilewithfragments.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_RESPONSE_PARAM = "response";
    private Response mResponse;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Response response) {
        ProfileFragment fragment = new ProfileFragment();
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(mResponse != null){
            binding.textViewName.setText(mResponse.getName());
            binding.textViewEmail.setText(mResponse.getEmail());
            binding.textViewEdu.setText(mResponse.getEducationLevel());
            binding.textViewMaritalStatus.setText(mResponse.getMaritalStatus());
            binding.textViewLivingStatus.setText(mResponse.getLivingStatus());
            binding.textViewIncomeValue.setText(mResponse.getIncomeRange());
        }
    }
}