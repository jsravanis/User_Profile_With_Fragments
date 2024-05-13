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
import edu.uncc.userprofilewithfragments.databinding.FragmentIdentificationBinding;

public class IdentificationFragment extends Fragment {

    FragmentIdentificationBinding binding;
    IdentificationListener mListener;

    public IdentificationFragment() {
        // Required empty public constructor
    }

    public interface IdentificationListener{
        public void startDemographicFragment(Response response);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIdentificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonNext.setOnClickListener(v -> {
            String name = binding.editTextName.getText().toString();
            String email = binding.editTextEmail.getText().toString();
            int checkedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
            if(name.isEmpty() || email.isEmpty()){
                Toast.makeText(getActivity(), "Name and Email shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if (checkedRadioButtonId == -1) {
                Toast.makeText(getActivity(), "Select any role", Toast.LENGTH_SHORT).show();
            }
            else{
                RadioButton selectedButton = view.findViewById(checkedRadioButtonId);
                String role = selectedButton.getText().toString();
                Response response = new Response(name, email, role);
                Log.d(MainActivity.TAG, "From Identification Fragment: "+response);
                mListener.startDemographicFragment(response);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (IdentificationListener) context;
    }
}