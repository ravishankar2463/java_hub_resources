package io.javahub.level0.gitversioncontrol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class GitObjectStoreTest {

    @Test
    @DisplayName("Should create Blob and compute identical SHA-1 for identical content")
    void testBlobHashing() {
        GitObjectStore store = new GitObjectStore();
        GitObjectStore.Blob b1 = GitObjectStore.Blob.create("Hello, Git!");
        GitObjectStore.Blob b2 = GitObjectStore.Blob.create("Hello, Git!");

        assertThat(b1.sha1()).isEqualTo(b2.sha1());
        store.put(b1);
        assertThat(store.get(b1.sha1())).isPresent();
    }

    @Test
    @DisplayName("Should link Commit to Tree and Parent Commit in DAG")
    void testCommitHistoryDag() {
        GitObjectStore store = new GitObjectStore();

        GitObjectStore.Blob blob = GitObjectStore.Blob.create("int x = 42;");
        store.put(blob);

        GitObjectStore.Tree tree1 = GitObjectStore.Tree.create(List.of(
            new GitObjectStore.TreeEntry("100644", "Main.java", blob.sha1())
        ));
        store.put(tree1);

        GitObjectStore.Commit initialCommit = GitObjectStore.Commit.create(
            tree1.sha1(), Optional.empty(), "Developer <dev@javahub.io>", "Initial commit"
        );
        store.put(initialCommit);

        GitObjectStore.Commit secondCommit = GitObjectStore.Commit.create(
            tree1.sha1(), Optional.of(initialCommit.sha1()), "Developer <dev@javahub.io>", "Second commit"
        );
        store.put(secondCommit);

        assertThat(secondCommit.parentSha1()).contains(initialCommit.sha1());
        assertThat(store.get(secondCommit.sha1())).contains(secondCommit);
    }
}
