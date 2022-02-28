package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Events(
    val available: String?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Item),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(available)
        parcel.writeString(collectionURI)
        parcel.writeTypedList(items)
        parcel.writeInt(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Events> {
        override fun createFromParcel(parcel: Parcel): Events {
            return Events(parcel)
        }

        override fun newArray(size: Int): Array<Events?> {
            return arrayOfNulls(size)
        }
    }
}