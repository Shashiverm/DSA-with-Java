// package leetcode.01_09_2026;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Give each litter cell a bit position.
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                litterId[i][j] = -1;

                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int fullMask = (1 << litterCount) - 1;

        // No litter to collect.
        if (fullMask == 0) {
            return 0;
        }

        /*
         * bestEnergy[r][c][mask] =
         * maximum remaining energy with which we've reached
         * (r, c) after collecting exactly the litter in mask.
         *
         * If we reach the same (r, c, mask) with less/equal energy,
         * that state can be discarded.
         */
        int[][][] bestEnergy = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                java.util.Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // State: row, col, mask, remaining energy
        java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();

        bestEnergy[sr][sc][0] = energy;
        queue.offer(new int[]{sr, sc, 0, energy});

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process one BFS level at a time.
            while (size-- > 0) {
                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int mask = state[2];
                int e = state[3];

                if (mask == fullMask) {
                    return steps;
                }

                // If energy is 0, we cannot make another move.
                // Being on R would already have reset energy when
                // entering that cell.
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int ne = e - 1;
                    int nmask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter.
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        nmask |= (1 << id);
                    }

                    // Reset energy upon entering R.
                    if (cell == 'R') {
                        ne = energy;
                    }

                    // Dominance pruning:
                    // If we've already reached this state with
                    // at least as much energy, this state is useless.
                    if (ne <= bestEnergy[nr][nc][nmask]) {
                        continue;
                    }

                    bestEnergy[nr][nc][nmask] = ne;
                    queue.offer(new int[]{nr, nc, nmask, ne});
                }
            }

            steps++;
        }

        return -1;
    }
}
