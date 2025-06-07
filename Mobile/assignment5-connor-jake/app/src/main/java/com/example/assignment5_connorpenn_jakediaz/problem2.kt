package com.example.assignment5_connorpenn_jakediaz

class problem2 {
    fun searchR(strings: Array<String>, str: String, first: Int, last: Int): Int {
        if (first > last) {
            return -1
        }

        var mid = (last + first) / 2

        if (strings[mid].isEmpty()) {
            var left = mid - 1
            var right = mid + 1

            while (true) {
                if (left < first && right > last) {
                    return -1
                } else if (right <= last && strings[right].isNotEmpty()) {
                    mid = right
                    break;
                } else if (left >= first && strings[left].isNotEmpty()){
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }
        if (str == strings[mid]) {
            return mid
        }
        else if (strings[mid].compareTo(str) < 0) {
            return searchR(strings, str, mid + 1, last)
        }
        else {
            return searchR(strings, str, first, mid - 1);
        }
    }

    public fun search(strings: Array<String>, str: String): Int {
        if (strings == null || str == null || str == "") {
            return -1
        }
        return searchR(strings,str,0,strings.size - 1)
    }
}