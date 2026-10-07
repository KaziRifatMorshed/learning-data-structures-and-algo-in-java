#include <iostream>
#include <string>
#include "add-concatanation-operator.h"
#include "infix-To-Postfix.h"
#include "re-to-nfa.h"
#include <map>
#include <set>
using namespace std;

int main()
{

    string re;
    cout << "Enter the regular expression: ";
    cin >> re;

    re = addConcatanation(re);
    cout << "After adding concatanation operator: " << re << endl;

    string postfix;
    infixToPostfix(re, postfix);
    cout << "Postfix: " << postfix << endl;

    NFA nfa = postfixToNFA(postfix);

    cout << "\nNFA Table: \n";

    cout << "Start: " << nfa.start << endl;
    cout << "End: " << nfa.end << endl;

    for (int i = 0; i <= nfa.end; i++)
    {
        for (auto &[u, w] : nfa.graph[i])
            cout << i << " " << u << " " << w << endl;
    }

    // set<char> st;
    // for (auto ch : postfix)
    // {
    //     if (isalnum(ch))
    //         st.insert(ch);
    // }
    // vector<char> edges;
    // for (auto ch : st)
    //     edges.push_back(ch);
    // edges.push_back('~');

    // map<pair<int, char>, vector<int>> mp;
    // for (int i = 0; i <= nfa.end; i++)
    // {
    //     for (auto &[u, w] : nfa.graph[i])
    //         mp[{i, w}].push_back(u);
    // }

    // for (auto e : edges)
    // {
    //     cout << "\t" << e;
    // }
    // cout << endl;
    // for (int i = 0; i <= nfa.end; i++)
    // {
    //     cout << i << "\t";
    //     for (auto e : edges)
    //     {
    //         if (mp[{i, e}].size() == 0)
    //             cout << "-";
    //         else
    //             for (auto state : mp[{i, e}])
    //             {
    //                 cout << state << ",";
    //             }
    //         cout << "\t";
    //     }
    //     cout << endl;
    // }
}