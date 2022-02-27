package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Stories(
    val available: Int,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArrayList(Item),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(available)
        parcel.writeString(collectionURI)
        parcel.writeTypedList(items)
        parcel.writeInt(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stories> {
        override fun createFromParcel(parcel: Parcel): Stories {
            return Stories(parcel)
        }

        override fun newArray(size: Int): Array<Stories?> {
            return arrayOfNulls(size)
        }
    }
}