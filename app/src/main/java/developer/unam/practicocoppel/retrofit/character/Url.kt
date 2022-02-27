package developer.unam.practicocoppel.retrofit.character

import android.os.Parcel
import android.os.Parcelable

data class Url(
    val type: String?,
    val url: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Url> {
        override fun createFromParcel(parcel: Parcel): Url {
            return Url(parcel)
        }

        override fun newArray(size: Int): Array<Url?> {
            return arrayOfNulls(size)
        }
    }
}