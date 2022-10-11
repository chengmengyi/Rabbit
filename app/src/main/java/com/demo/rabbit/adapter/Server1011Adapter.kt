package com.demo.rabbit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.rabbit.R
import com.demo.rabbit.app.getServerFlag
import com.demo.rabbit.entity.Server1011Entity
import com.demo.rabbit.server.ConnectHelper
import com.demo.rabbit.server.ServerInfoHelper
import kotlinx.android.synthetic.main.server_item_layout.view.*

class Server1011Adapter(
    private val context: Context,
    private val item:(entity:Server1011Entity)->Unit
):RecyclerView.Adapter<Server1011Adapter.Server1011View>() {
    private val serverList= arrayListOf<Server1011Entity>()
    init {
        serverList.add(ServerInfoHelper.createRandomServer())
        serverList.addAll(ServerInfoHelper.get1011ServerList())
    }


    inner class Server1011View(view:View):RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                item.invoke(serverList[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Server1011View {
        return Server1011View(LayoutInflater.from(context).inflate(R.layout.server_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: Server1011View, position: Int) {
        with(holder.itemView){
            val server1011Entity = serverList[position]
            tv_server_name.text=server1011Entity.rabbit_1011_country
            iv_server_country.setImageResource(getServerFlag(server1011Entity.rabbit_1011_country))
            val b = server1011Entity.rabbit_1011_host == ConnectHelper.server1011Entity.rabbit_1011_host
            item_layout.isSelected=b
            icon_select.isSelected=b
        }
    }

    override fun getItemCount(): Int = serverList.size
}