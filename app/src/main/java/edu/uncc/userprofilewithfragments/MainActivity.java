package edu.uncc.userprofilewithfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uncc.userprofilewithfragments.databinding.ActivityMainBinding;
import edu.uncc.userprofilewithfragments.fragments.DemographicFragment;
import edu.uncc.userprofilewithfragments.fragments.IdentificationFragment;
import edu.uncc.userprofilewithfragments.fragments.MainFragment;
import edu.uncc.userprofilewithfragments.fragments.ProfileFragment;
import edu.uncc.userprofilewithfragments.fragments.Response;
import edu.uncc.userprofilewithfragments.fragments.SelectEducationFragment;
import edu.uncc.userprofilewithfragments.fragments.SelectIncomeFragment;
import edu.uncc.userprofilewithfragments.fragments.SelectLivingStatusFragment;
import edu.uncc.userprofilewithfragments.fragments.SelectMaritalStatusFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.MainListener,
        IdentificationFragment.IdentificationListener, DemographicFragment.DemographicListener,
        SelectEducationFragment.EducationListener, SelectMaritalStatusFragment.MaritalStatusListener,
        SelectLivingStatusFragment.LivingStatusListener, SelectIncomeFragment.IncomeListener {
    public static final String TAG = "Profile";
    public static final String DEMOGRAPHIC = "Demographic";
    private Response response;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    @Override
    public void startIdentificationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new IdentificationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startDemographicFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, DemographicFragment.newInstance(response), DEMOGRAPHIC)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startSelectEducationFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, SelectEducationFragment.newInstance(response))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startSelectMaritalStatusFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, SelectMaritalStatusFragment.newInstance(response))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startSelectLivingStatusFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, SelectLivingStatusFragment.newInstance(response))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startSelectIncomeFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, SelectIncomeFragment.newInstance(response))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startProfileFragment(Response response) {
        this.response = response;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(response))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDemographicFragment(Response response) {
        this.response = response;
        DemographicFragment demographicFragment =
                (DemographicFragment) getSupportFragmentManager().findFragmentByTag(DEMOGRAPHIC);
        if (demographicFragment != null) {
            demographicFragment.setResponse(response);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelGoToDemographicFragment() {
        getSupportFragmentManager().popBackStack();
    }
}