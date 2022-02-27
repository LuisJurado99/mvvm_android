package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Comics(
    val available: Int,
    val items: List<Item>?,
    val returned: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Item),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(available)
        parcel.writeTypedList(items)
        parcel.writeInt(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comics> {
        override fun createFromParcel(parcel: Parcel): Comics {
            return Comics(parcel)
        }

        override fun newArray(size: Int): Array<Comics?> {
            return arrayOfNulls(size)
        }
    }
}