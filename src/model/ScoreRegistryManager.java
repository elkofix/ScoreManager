package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreRegistryManager {

    private Node root;

    private static class Node {
        private ScoreRegistry scoreRegistry;
        private Node left, right;

        public Node(ScoreRegistry scoreRegistry) {
            this.scoreRegistry = scoreRegistry;
        }
    }

    public void addScoreRegistry(ScoreRegistry scoreRegistry) {
        root = addScoreRegistry(root, scoreRegistry);
    }

    private Node addScoreRegistry(Node node, ScoreRegistry scoreRegistry) {
        if (node == null) {
            return new Node(scoreRegistry);
        }
        if (scoreRegistry.getUsername().compareTo(node.scoreRegistry.getUsername()) < 0) {
            node.left = addScoreRegistry(node.left, scoreRegistry);
        } else if (scoreRegistry.getUsername().compareTo(node.scoreRegistry.getUsername()) > 0) {
            node.right = addScoreRegistry(node.right, scoreRegistry);
        } else {
            node.scoreRegistry = scoreRegistry;
        }
        return node;
    }

    public ScoreRegistry searchByScore(int score) {
        return searchByScore(root, score);
    }

    private ScoreRegistry searchByScore(Node node, int score) {
        if (node == null) {
            return null;
        }
        if (score == node.scoreRegistry.getScore()) {
            return node.scoreRegistry;
        }
        if (score < node.scoreRegistry.getScore()) {
            return searchByScore(node.left, score);
        } else {
            return searchByScore(node.right, score);
        }
    }

    public List<ScoreRegistry> alphabeticalOrder() {
        List<ScoreRegistry> scoreList = new ArrayList<>();
        alphabeticalOrder(root, scoreList);
        return scoreList;
    }

    private void alphabeticalOrder(Node node, List<ScoreRegistry> scoreList) {
        if (node != null) {
            alphabeticalOrder(node.left, scoreList);
            scoreList.add(node.scoreRegistry);
            alphabeticalOrder(node.right, scoreList);
        }
    }

    public List<ScoreRegistry> top5() {
        List<ScoreRegistry> scoreList = alphabeticalOrder();
        Collections.sort(scoreList, new Comparator<ScoreRegistry>() {
            @Override
            public int compare(ScoreRegistry sr1, ScoreRegistry sr2) {
                return sr2.getScore() - sr1.getScore();
            }
        });
        return scoreList.subList(0, Math.min(scoreList.size(), 5));
    }

}
