You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

    You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
    Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
    Once you reach a tree with fruit that cannot fit in your baskets, you must stop.

Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.

Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


class Solution {
    Integer one=null;
    Integer two=null;
    private boolean canPick(int pick) {
        if (one == null) {
            one = pick;
            return true;
        }
        if (two == null) {
            two = pick;
            return true;
        }
        return (one == pick || two == pick);
    }
    private int findEnd(int idx, int[] f) {
        if (idx >= f.length) return 0;
        one=null;
        two=null;
        int i=idx;
        while(i<f.length && canPick(f[i])) { i++; }
        return i;
    }
    public int totalFruit(int[] fruits) {
        int max = -1;
        if (fruits == null) return 0;
        for (int i=0; i<fruits.length; i++) {
            int end = findEnd(i, fruits);
            if (end - i > max) {
                max = end - i;
            }
        }
        return max + 1;
    }
}



Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.

    For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.

If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.

    For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.

If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.

    For example, "m.y+name@email.com" will be forwarded to "my@email.com".

It is possible to use both of these rules at the same time.

Given an array of strings emails where we send one email to each emails[i], return the number of different addresses that actually receive mails.

 

Example 1:

Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.

Example 2:

Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
Output: 3

 

Constraints:

    1 <= emails.length <= 100
    1 <= emails[i].length <= 100
    emails[i] consist of lowercase English letters, '+', '.' and '@'.
    Each emails[i] contains exactly one '@' character.
    All local and domain names are non-empty.
    Local names do not start with a '+' character.
    Domain names end with the ".com" suffix.




class Solution {
    private String cannon(String email) {
        if (email == null) return null;
        String[] parts = email.split('@');
        if (parts.length != 2) return null;
        String nodot = parts[0].replaceAll("\\.", "");
        int idx = nodot.indexOf('+');
        StringBuilder sb = new StringBuilder();
        if (idx < 0) {
            sb.append(nodot);
        } else {
            sb.append(nodot.substring(0, idx));
        }
        sb.append("@").append(parts[1]);
        return sb.toString();
    }
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqs = new HashSet<String>();
        for (String s : emails) {
	  if (cannon(s) != null) {
            uniqs.add(cannon(s));
	  }
        }
        return uniqs.size();
    }
}


class Solution {
    private String cannon(String email) {
        if (email == null) return null;
        String[] parts = email.split("@");
        if (parts.length != 2) return null;
        String nodot = parts[0].replaceAll("\\.", "");
        int idx = nodot.indexOf('+');
        String name = (idx < 0) ? nodot : nodot.substring(0, idx);
        return new StringBuilder(name).append("@").append(parts[1]).toString();
    }
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqs = new HashSet<String>();
        for (String s : emails) {
            String clean = cannon(s);
            if (clean != null) {
                uniqs.add(clean);
            }
        }
        return uniqs.size();
    }
}

class Solution {
    Integer one=null;
    Integer two=null;
    private boolean canPick(int pick) {
        if (one == null) {
            one = pick;
            return true;
        }
        if (two == null) {
            if (one != pick) {
                two = pick;
            }
            return true;
        }
        return (one == pick || two == pick);
    }
    
    private int findEnd(int idx, int[] f) {
        if (idx >= f.length) return -2;
        one=null;
        two=null;
        int i=idx;
        while(i<f.length && canPick(f[i])) { i++; }
        return i;
    }
    
    public int totalFruit(int[] fruits) {
        int max = -1;
        if (fruits == null) return 0;
        for (int i=0; i<fruits.length; i++) {
            int end = findEnd(i, fruits) - 1;
            if (end - i > max) {
                max = end - i;
            }
        }
        return max + 1;
    }
}
