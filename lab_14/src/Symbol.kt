package src

data class Symbol(var index: Int?) {
    fun addSymbol(index: Int) {
        if (nextSymbol == null){
            nextSymbol = Symbol(index)
        } else {
            nextSymbol?.addSymbol(index)
        }
    }

    //    var char: Char? = null
    var nextSymbol: Symbol? = null
}
