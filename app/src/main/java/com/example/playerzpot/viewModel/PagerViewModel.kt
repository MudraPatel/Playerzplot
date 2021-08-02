package com.example.playerzpot.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.playerzpot.model.BucketModel
import com.example.playerzpot.model.StoryModel
import org.json.JSONObject


class PagerViewModel(application: Application) : AndroidViewModel(application) {
    private val storyListLiveData: MutableLiveData<ArrayList<StoryModel>> = MutableLiveData<ArrayList<StoryModel>>();
    private val bucketListLiveData: MutableLiveData<ArrayList<BucketModel>> = MutableLiveData<ArrayList<BucketModel>>();
    val bucketDetailLiveData: MutableLiveData<BucketModel> = MutableLiveData<BucketModel>();

    fun getStoryList(jsonObject : JSONObject) : MutableLiveData<ArrayList<StoryModel>> {
        val isSuccess = jsonObject.getString("message")
        var storyModelList : ArrayList<StoryModel> = ArrayList()

        if(isSuccess.equals("Success", true)) {
            val arrayStory = jsonObject.getJSONArray("data")
            for (i in 0 until arrayStory.length()) {
                val detail = arrayStory.getJSONObject(i)
                var storyModel: StoryModel =
                    StoryModel(
                        detail.getInt("id"),
                        detail.getString("team_1"),
                        detail.getString("team_2"),
                        detail.getString("team_1_score"),
                        detail.getString("team_2_score"),
                        detail.getString("live_message"),

                    )
                storyModelList.add(storyModel)
            }
        }
        storyListLiveData.value = storyModelList

        return storyListLiveData
    }

    fun getBucketList(jsonObject : JSONObject, isVideo: Boolean) : MutableLiveData<ArrayList<BucketModel>> {
        val isSuccess = jsonObject.getString("message")
        val bucketModelList : ArrayList<BucketModel> = ArrayList()

        if(isSuccess.equals("Success", true)) {
            Log.e("Result", isSuccess +":IF")
            val arrayBucket = jsonObject.getJSONArray("data")
            for (i in 0 until arrayBucket.length()) {
                val detail = arrayBucket.getJSONObject(i)
                val bucketModel =
                    BucketModel(
                        detail.getInt("id"),
                        detail.getString("title"),
                        detail.getString("imageview"),
                        detail.getString("details"),
                        detail.getString("viewed"),
                        detail.getBoolean("isFavorite"),
                        detail.getString("videoTime"),
                        detail.getBoolean("isVideo")
                   )

                if(isVideo){
                    if(bucketModel.isVideo)
                        bucketModelList.add(bucketModel)
                }
                else {
                    bucketModelList.add(bucketModel)
                }
            }
        }
        bucketListLiveData.value = bucketModelList
        return bucketListLiveData
    }

    fun getBucketDetails(jsonObject : JSONObject, bucketId: Int) {
        val isSuccess = jsonObject.getString("message")
        if(isSuccess.equals("Success", true)) {
            val arrayBucket = jsonObject.getJSONArray("data")
            for (i in 0 until arrayBucket.length()) {
                val detail = arrayBucket.getJSONObject(i)
                val bucketModel =
                    BucketModel(
                        detail.getInt("id"),
                        detail.getString("title"),
                        detail.getString("imageview"),
                        detail.getString("details"),
                        detail.getString("viewed"),
                        detail.getBoolean("isFavorite"),
                        detail.getString("videoTime"),
                        detail.getBoolean("isVideo")
                    )

                if(bucketId == bucketModel.id){
                   bucketDetailLiveData.value = bucketModel
                }
            }
        }

    }

}
