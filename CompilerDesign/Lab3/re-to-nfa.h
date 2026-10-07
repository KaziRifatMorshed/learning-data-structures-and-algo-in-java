#include <iostream>
#include <string>
#include <stack>
#include <vector>
// using thomsons rule
struct NFA
{
    int start;
    int end;
    vector<vector<pair<int, char>>> graph;

    NFA()
    {
        graph.resize(100);
    }
    NFA(int s, int e) : NFA()
    {
        start = s;
        end = e;
    }
    NFA(int s, int e, char ch) : NFA(s, e)
    {
        graph[start].push_back({e, ch});
    }

    void merge(NFA nfa)
    {
        for (int i = 0; i <= nfa.end; i++)
        {
            for (auto &it : nfa.graph[i])
            {
                graph[i].push_back(it);
            }
        }
    }
    void merge(NFA nfa1, NFA nfa2)
    {
        merge(nfa1);
        merge(nfa2);
    }
    void kleeneClosure(NFA nfa)
    {
        merge(nfa);
        graph[start].push_back({nfa.start, '~'});
        graph[start].push_back({end, '~'});
        graph[nfa.end].push_back({end, '~'});
        graph[nfa.end].push_back({nfa.start, '~'});
    }
    void positiveClosure(NFA nfa)
    {
        merge(nfa);
        graph[start].push_back({nfa.start, '~'});
        graph[nfa.end].push_back({end, '~'});
        graph[nfa.end].push_back({nfa.start, '~'});
    }
    void optional(NFA nfa)
    {
        merge(nfa);
        graph[start].push_back({nfa.start, '~'});
        graph[start].push_back({end, '~'});
        graph[nfa.end].push_back({end, '~'});
    }
    void concatanation(NFA nfa1, NFA nfa2)
    {
        merge(nfa1, nfa2);
        graph[nfa1.end].push_back({nfa2.start, '~'});
    }
    void OR(NFA nfa1, NFA nfa2)
    {
        merge(nfa1, nfa2);
        graph[start].push_back({nfa1.start, '~'});
        graph[start].push_back({nfa2.start, '~'});

        graph[nfa1.end].push_back({end, '~'});
        graph[nfa2.end].push_back({end, '~'});
    }
};

void kleeneClosure(stack<NFA> &stack, int &state)
{
    NFA nfa;
    if (stack.size() > 0)
    {
        nfa = stack.top();
        stack.pop();
    }
    else
        return;
    NFA new_nfa = NFA(state, state + 1);
    state += 2;
    new_nfa.kleeneClosure(nfa);
    stack.push(new_nfa);
}
void positiveClosure(stack<NFA> &stack, int &state)
{
    NFA nfa;
    if (stack.size() > 0)
    {
        nfa = stack.top();
        stack.pop();
    }
    else
        return;
    NFA new_nfa = NFA(state, state + 1);
    state += 2;
    new_nfa.positiveClosure(nfa);
    stack.push(new_nfa);
}
void optional(stack<NFA> &stack, int &state)
{
    NFA nfa;
    if (stack.size() > 0)
    {
        nfa = stack.top();
        stack.pop();
    }
    else
        return;
    NFA new_nfa = NFA(state, state + 1);
    state += 2;
    new_nfa.optional(nfa);
    stack.push(new_nfa);
}
void concatanation(stack<NFA> &stack, int &state)
{
    NFA nfa1, nfa2;
    if (stack.size() > 0)
    {
        nfa2 = stack.top();
        stack.pop();
        nfa1 = stack.top();
        stack.pop();
    }
    else
        return;
    NFA new_nfa = NFA(nfa1.start, nfa2.end);
    new_nfa.concatanation(nfa1, nfa2);
    stack.push(new_nfa);
}
void OR(stack<NFA> &stack, int &state)
{
    NFA nfa1, nfa2;
    if (stack.size() > 0)
    {
        nfa2 = stack.top();
        stack.pop();
        nfa1 = stack.top();
        stack.pop();
    }
    else
        return;
    NFA new_nfa = NFA(state, state + 1);
    state += 2;
    new_nfa.OR(nfa1, nfa2);
    stack.push(new_nfa);
}
NFA postfixToNFA(string &postfix)
{
    stack<NFA> stack;
    int state = 0;
    for (auto ch : postfix)
    {
        if (ch == '*')
            kleeneClosure(stack, state);
        else if (ch == '+')
            positiveClosure(stack, state);
        else if (ch == '?')
            optional(stack, state);
        else if (ch == '.')
            concatanation(stack, state);
        else if (ch == '|')
            OR(stack, state);
        else
        {
            NFA new_nfa = NFA(state, state + 1, ch);
            state += 2;
            stack.push(new_nfa);
        }
    }
    if (stack.empty())
        return NFA();
    return stack.top();
}
