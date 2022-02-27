package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Series(
    val available: String?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Item),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(available)
        parcel.writeString(collectionURI)
        parcel.writeTypedList(items)
        parcel.writeString(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Series> {
        override fun createFromParcel(parcel: Parcel): Series {
            return Series(parcel)
        }

        override fun newArray(size: Int): Array<Series?> {
            return arrayOfNulls(size)
        }
    }
}