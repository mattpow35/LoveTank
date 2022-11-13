package trishie.cs.byu.lovetank;

import redis.clients.jedis.Jedis;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import org.w3c.dom.Text;
import java.lang.reflect.Array;
import java.util.ArrayList;

import trishie.cs.byu.lovetank.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText wife_firstName;
    private EditText husband_firstName;
    private EditText wife_phoneNumber;
    private EditText husband_phoneNumber;
    private TextView wife_loveLanguages;
    private TextView husband_loveLanguages;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        wife_firstName = binding.wifeNameField;
        husband_firstName = binding.husbandNameField;
        wife_phoneNumber = binding.wifePhoneField;
        husband_phoneNumber = binding.husbandsPhoneField;
        wife_loveLanguages = binding.loveLanguageDropdown;
        husband_loveLanguages = binding.loveLanguageDropdown2;

        //        wife_firstName = (EditText) binding.findViewById(R.id.wifeNameField);
//        husband_firstName = (EditText) view.findViewById(R.id.husbandNameField);
//        wife_phoneNumber = (EditText) getActivity().findViewById(R.id.wifePhoneField);
//        husband_phoneNumber = (EditText) getActivity().findViewById(R.id.husbandsPhoneField);
//        wife_loveLanguages = (TextView) getActivity().findViewById(R.id.loveLanguageDropdown);
//        husband_loveLanguages = (TextView) getActivity().findViewById(R.id.loveLanguageDropdown2);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //button_first
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Jedis jedis = new Jedis("localhost", 6379);
                System.out.println("connected to reddis");

                String wifesName = wife_firstName.getText().toString();
                String wifes_phone = wife_phoneNumber.getText().toString();
                String wife_languages = wife_loveLanguages.getText().toString();
                String husbandsName = husband_firstName.getText().toString();
                String husbands_phone = husband_phoneNumber.getText().toString();
                String husbands_languages = husband_loveLanguages.getText().toString();

                String[] arrOfHus = husbands_languages.split(", ");
                String[] arrOfWife = wife_languages.split(", ");

                System.out.println("wife name " + wifesName);
                System.out.println("wife number " + wifes_phone);
                System.out.println("wife love languages " + arrOfWife[0]);
                System.out.println("wife love languages " + arrOfWife[1]);
                System.out.println("husbands name " + husbandsName);
                System.out.println("husbands phone " + husbands_phone );
                System.out.println("husbands love lang " + arrOfHus[0]);
                System.out.println("husbands love lang " + arrOfHus[1]);

                //set if non existant
                jedis.setnx(husbands_phone + ":name", husbandsName);
                jedis.setnx(husbands_phone + ":partner_num", wifes_phone);
                jedis.setnx(husbands_phone + ":love_lang_1", arrOfHus[0]);
                jedis.setnx(husbands_phone + ":love_lang_2", arrOfHus[1]);
                jedis.setnx(husbands_phone + ":partner_name", wifesName);
                jedis.lpush("phone_numbers", husbands_phone);

                jedis.setnx(wifes_phone + ":name", wifesName);
                jedis.setnx(wifes_phone + ":partner_num", husbands_phone);
                jedis.setnx(wifes_phone + ":love_lang_1", arrOfWife[0]);
                jedis.setnx(wifes_phone + ":love_lang_2", arrOfWife[1]);
                jedis.setnx(wifes_phone + ":partner_name", husbandsName);
                jedis.lpush("phone_numbers", wifes_phone);


                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //assign variable

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}