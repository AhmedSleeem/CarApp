package ahmed.adel.sleeem.clowyy.carapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import ahmed.adel.sleeem.clowyy.carapp.R;
import ahmed.adel.sleeem.clowyy.carapp.model.Car;

public class CarAdapter extends PagedListAdapter<Car,CarAdapter.CarViewHolder> {


   private Context context;
    public CarAdapter(Context context) {
        super(CarAdapter.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        Car car = getItem(position);

        holder.bind(car);


    }

    class CarViewHolder extends RecyclerView.ViewHolder{

        private ImageView carImg ;
        private TextView brandName;
        private TextView constructionYearTv;
        private TextView isUsedTv;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carImg = itemView.findViewById(R.id.carImg);

            brandName = itemView.findViewById(R.id.brandNameTv);
            constructionYearTv = itemView.findViewById(R.id.constructionYearTv);
            isUsedTv = itemView.findViewById(R.id.isUsedTv);
        }

        public void bind(Car car){
            brandName.setText(car.getBrand());
            constructionYearTv.setText(car.getConstractionYear());

            String usedOrNot = car.getIsUsed()?"Used":"New";
            isUsedTv.setText(usedOrNot);


            Glide.with(carImg.getContext())
                    .load(car.getImageUrl())
                    .placeholder(R.drawable.carplaceholder)
                    .into(carImg);
        }
    }


    public static final DiffUtil.ItemCallback<Car> CALLBACK = new DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.equals(newItem);
        }
    };
}
