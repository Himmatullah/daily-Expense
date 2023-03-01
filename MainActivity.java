import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<Expense> expenseList = new ArrayList<>();
    private EditText etExpenseName, etExpenseAmount;
    private TextView tvTotalExpenses, tvExpenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etExpenseName = findViewById(R.id.etExpenseName);
        etExpenseAmount = findViewById(R.id.etExpenseAmount);
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses);
        tvExpenseList = findViewById(R.id.tvExpenseList);
    }

    public void addExpense(View view) {
        String name = etExpenseName.getText().toString();
        double amount = Double.parseDouble(etExpenseAmount.getText().toString());
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        Expense expense = new Expense(name, amount, date);
        expenseList.add(expense);
        updateUI();
        etExpenseName.setText("");
        etExpenseAmount.setText("");
    }

    private void updateUI() {
        double totalExpenses = 0;
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenseList) {
            totalExpenses += expense.getAmount();
            sb.append(expense.getName()).append(" - ").append(expense.getAmount()).append(" - ").append(expense.getDate()).append("\n");
        }
        tvTotalExpenses.setText(String.format(Locale.getDefault(), "Total expenses: $%.2f", totalExpenses));
        tvExpenseList.setText(sb.toString());
    }
}
