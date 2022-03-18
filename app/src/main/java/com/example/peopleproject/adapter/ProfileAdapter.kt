package com.example.peopleproject


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peopleproject.data.Data
import com.example.peopleproject.databinding.ProfileItemViewBinding


class ProfileAdapter :RecyclerView.Adapter<ProfileViewHolder>(){

    var profileList = mutableListOf<Data>()
    fun setProfiles(profiles :List<Data>){
        this.profileList = profiles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {

        val binding=ProfileItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.binding.tvEmail.text = profileList[position].email
        holder.binding.tvFirstname.text = profileList[position].first_name
        holder.binding.tvLastname.text = profileList[position].last_name
        Glide.with(holder.itemView.context).load(profileList[position].avatar).into(holder.binding.imgAvatar)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
}

class ProfileViewHolder(val binding: ProfileItemViewBinding):RecyclerView.ViewHolder(binding.root) {

}
