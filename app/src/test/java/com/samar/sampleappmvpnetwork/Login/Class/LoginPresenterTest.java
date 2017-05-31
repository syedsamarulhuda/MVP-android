package com.samar.sampleappmvpnetwork.Login.Class;

import android.widget.EditText;

import com.samar.sampleappmvpnetwork.Login.Interface.ILoginInteractor;
import com.samar.sampleappmvpnetwork.Login.Interface.ILoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by samar on 25/05/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private ILoginView view;
    @Mock
    private LoginPresenter presenter;
    @Mock
    private LoginInteractor interactor;



    @Before
    public void setUp() throws Exception {

        presenter = new LoginPresenter(view);

    }

    @Test
    public void checkIfShowsProgressOnResume() {
        presenter.onResume();
        verify(view, times(1)).showProgress();
    }





}