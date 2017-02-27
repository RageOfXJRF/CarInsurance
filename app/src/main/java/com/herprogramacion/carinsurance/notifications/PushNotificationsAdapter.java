package com.herprogramacion.carinsurance.notifications;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.carinsurance.R;
import com.herprogramacion.carinsurance.data.PushNotification;

import java.util.ArrayList;

/**
 * Adaptador de notificaciones
 */
public class PushNotificationsAdapter
        extends RecyclerView.Adapter<PushNotificationsAdapter.ViewHolder> {

    ArrayList<PushNotification> pushNotifications = new ArrayList<>();

    public PushNotificationsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final PushNotification newNotification = pushNotifications.get(position);

        holder.title.setText(newNotification.getTitle());
        holder.description.setText(newNotification.getDescription());
        holder.expiryDate.setText(String.format("Válido hasta %s", newNotification.getExpiryDate()));
        holder.discount.setText(newNotification.getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(v.getContext(),newNotification.getTitle()+"\n"
                        +"\n"+newNotification.getDescription()+"\n"+newNotification.getExpiryDate()
                        +"\n"+newNotification.getDiscount(),Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pushNotifications.size();
    }

    public void replaceData(ArrayList<PushNotification> items) {
        setList(items);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<PushNotification> list) {
        this.pushNotifications = list;
    }

    public void addItem(PushNotification pushMessage) {
        pushNotifications.add(0, pushMessage);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView expiryDate;
        public TextView discount;
        public Button boton;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            expiryDate = (TextView) itemView.findViewById(R.id.tv_expiry_date);
            discount = (TextView) itemView.findViewById(R.id.tv_discount);
            boton = (Button) itemView.findViewById(R.id.button);
            boton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition());
                }
            });
            /*itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    datos(getAdapterPosition(),v.getContext());
                }
            });*/
        }
    }

    public void delete(int position) { //removes the row
        pushNotifications.remove(position);
        notifyItemRemoved(position);
    }

    /*public void datos(int position, Context context)
    {
        Toast tast = Toast.makeText(context,pushNotifications.get(position).toString(),Toast.LENGTH_LONG);
        tast.show();
    }*/
}
